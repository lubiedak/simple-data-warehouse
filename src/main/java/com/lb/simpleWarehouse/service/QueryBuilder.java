package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.QueryEnums.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lb.simpleWarehouse.db.QueryEnums.*;

@Service
public class QueryBuilder {

    public String buildQuery(List<Metric> metrics, List<Dimension> dimensions, Map<Filter, String> filters){
        var query = S;
        query += metrics.stream().map(Metric::query).collect(Collectors.joining());
        query += dimensions.stream().map(Dimension::query).collect(Collectors.joining());
        query = fixComma(query);
        query += filters.keySet().isEmpty() ? FROM_CAMPAIGNS_TABLE : FROM_CAMPAIGNS_WHERE;
        query += filters.keySet().stream().map(Filter::query).collect(Collectors.joining());
        query = fixComma(query);
        query += "GROUP BY" + dimensions.stream().map(d->" c."+d + ",").collect(Collectors.joining());
        query = fixComma(query);
        return query;
    }

    private String fixComma(String query){
        if(query.endsWith(", ")){
            query = query.substring(0, query.length()-2);
        }
        if(query.endsWith(",")){
            query = query.substring(0, query.length()-1);
        }
        return query;
    }
}
