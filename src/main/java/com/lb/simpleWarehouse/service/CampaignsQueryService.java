package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.QueryEnums.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CampaignsQueryService {

    private final QueryBuilder queryBuilder;
    @PersistenceContext
    private final EntityManager em;

    public List<Object> query(List<Metric> metrics, List<Dimension> dimensions, Map<Filter, String> filters){
        var rawResults = em.createNativeQuery(queryBuilder.buildQuery(metrics,dimensions,filters)).getResultList();
        return prepareResults(rawResults, metrics, dimensions);
    }

    ArrayList<Object> prepareResults(List<Object[]> results, List<Metric> metrics, List<Dimension> dimensions){
        var columns = metrics.stream().map(Metric::name).collect(Collectors.toList());
        columns.addAll(dimensions.stream().map(Dimension::name).collect(Collectors.toList()));

        var resultsWithColumns = new ArrayList<>();
        for(var result : results){
            resultsWithColumns.add(mergeColumnsWitResults(columns, result));
        }
        return resultsWithColumns;
    }

    private Map<String, String> mergeColumnsWitResults(List<String> columns, Object[] result) {
        var resultMap = new TreeMap<String, String>();
        for(int i = 0; i< result.length; i++){
            if(columns.get(i).equals(Metric.ctr.name())){
                resultMap.put(columns.get(i), String.format("%f",result[i]));
            } else {
                resultMap.put(columns.get(i), result[i].toString());
            }
        }
        return resultMap;
    }
}
