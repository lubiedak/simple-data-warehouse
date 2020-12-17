package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.QueryEnums.Dimension;
import com.lb.simpleWarehouse.db.QueryEnums.Filter;
import com.lb.simpleWarehouse.db.QueryEnums.Metric;
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

    public List<Object> query(List<Metric> metrics, List<Dimension> dimensions, Map<Filter, String> filters) {

        var query = em.createNativeQuery(queryBuilder.buildQuery(metrics, dimensions, filters));
        for (var e : filters.entrySet()) {
            query.setParameter(e.getKey().name(), e.getValue());
        }
        /*
        var query = em.createNativeQuery("SELECT SUM(c.clicks) as clicks, SUM(c.impressions) as impressions, " +
                "1.0*SUM(c.clicks)/SUM(c.impressions) as ctr, c.campaign as campaign FROM Campaigns c " +
                "WHERE c.daily >= CAST('2013-05-03' AS DATE) AND c.daily <= CAST('2020-05-03' AS DATE) GROUP BY c.campaign");
                */


        var rawResults = query.getResultList();
        return prepareResults(rawResults, metrics, dimensions);
    }

    ArrayList<Object> prepareResults(List<Object[]> results, List<Metric> metrics, List<Dimension> dimensions) {
        var columns = metrics.stream().map(Metric::name).collect(Collectors.toList());
        columns.addAll(dimensions.stream().map(Dimension::name).collect(Collectors.toList()));

        var resultsWithColumns = new ArrayList<>();
        for (var result : results) {
            resultsWithColumns.add(mergeColumnsWitResults(columns, result));
        }
        return resultsWithColumns;
    }

    private Map<String, String> mergeColumnsWitResults(List<String> columns, Object[] result) {
        var resultMap = new TreeMap<String, String>();
        var ctrPresent = columns.contains(Metric.ctr.name());
        for (int i = 0; i < result.length; i++) {
            if (ctrPresent && columns.get(i).equals(Metric.ctr.name())) {
                resultMap.put(columns.get(i), String.format("%f", result[i]));
            } else {
                resultMap.put(columns.get(i), result[i].toString());
            }
        }
        return resultMap;
    }
}
