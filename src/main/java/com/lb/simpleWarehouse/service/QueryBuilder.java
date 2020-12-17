package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.QueryEnums.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lb.simpleWarehouse.db.QueryEnums.*;

@Slf4j
@Service
public class QueryBuilder {

    public String buildQuery(List<Metric> metrics, List<Dimension> dimensions, Map<Filter, String> filters) {
        var query = prepareSelect(metrics, dimensions);
        query = prepareWhereStatements(query, filters);
        query += "GROUP BY" + dimensions.stream().map(d -> " c." + d + ",").collect(Collectors.joining());
        query = fixComma(query);
        log.info(query);
        return query;
    }

    private String fixComma(String query) {
        if (query.endsWith(", ")) {
            query = query.substring(0, query.length() - 2);
        }
        if (query.endsWith(",")) {
            query = query.substring(0, query.length() - 1);
        }
        return query;
    }

    private String prepareSelect(List<Metric> metrics, List<Dimension> dimensions) {
        var query = S;
        query += metrics.stream().map(Metric::query).collect(Collectors.joining());
        query += dimensions.stream().map(Dimension::query).collect(Collectors.joining());
        return fixComma(query);
    }

    private String prepareWhereStatements(String query, Map<Filter, String> filters) {
        query += filters.isEmpty() ? FROM_CAMPAIGNS_TABLE : FROM_CAMPAIGNS_WHERE;
        if (!filters.isEmpty()) {
            query += filters.entrySet().stream().map(e -> e.getKey().query() + "AND ").collect(Collectors.joining());
            query = query.substring(0, query.length() - 4);
        }
        return query;
    }
}
