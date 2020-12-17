package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.QueryEnums.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import static com.lb.simpleWarehouse.db.QueryEnums.GROUP_BY;
import static org.assertj.core.api.Assertions.assertThat;

class QueryBuilderTest {

    @Test
    public void simpleQuery(){
        final var queryBuilder = new QueryBuilder();
        var actualQuery = queryBuilder.buildQuery(Arrays.asList(Metric.clicks), Arrays.asList(Dimension.campaign), new HashMap<>());
        var expectedQuery = "SELECT SUM(c.clicks) as clicks, c.campaign as campaign FROM Campaigns c GROUP BY c.campaign";
        assertThat(actualQuery).isEqualTo(expectedQuery);
    }

    @Test
    public void queryWithCtr(){
        final var queryBuilder = new QueryBuilder();
        var actualQuery = queryBuilder.buildQuery(Arrays.asList(Metric.ctr), Arrays.asList(Dimension.datasource), new HashMap<>());
        var expectedQuery = "SELECT 1.0*SUM(c.clicks)/SUM(c.impressions) as ctr, " +
                "c.datasource as datasource FROM Campaigns c GROUP BY c.datasource";
        assertThat(actualQuery).isEqualTo(expectedQuery);
    }

    @Test
    public void queryWithFilters(){
        final var queryBuilder = new QueryBuilder();
        final var filters = new TreeMap<Filter, String>();
        filters.put(Filter.datasource, "Google");
        filters.put(Filter.campaign, "Company");
        var actualQuery = queryBuilder.buildQuery(Arrays.asList(Metric.clicks), Arrays.asList(Dimension.campaign), filters);
        var expectedQuery = "SELECT SUM(c.clicks) as clicks, c.campaign as campaign FROM Campaigns c WHERE " +
                "c.campaign = :campaign AND c.datasource = :datasource GROUP BY c.campaign";
        assertThat(actualQuery).isEqualTo(expectedQuery);
    }

}