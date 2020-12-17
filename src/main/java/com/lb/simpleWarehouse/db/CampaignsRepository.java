package com.lb.simpleWarehouse.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.lb.simpleWarehouse.db.QueryEnums.*;

public interface CampaignsRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByCampaign(String campaign);
    List<Campaign> findAllByDatasource(String datasource);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ CAMPAIGN + DATASOURCE + FROM_WHERE + DATASOURCE_EQ + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForDataSourcePerCampaign(
            @Param("datasource") String datasource);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ CAMPAIGN + DATASOURCE + FROM_WHERE + CAMPAIGN_EQ + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForCampaignPerDatasource(
            @Param("campaign") String campaign);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ CAMPAIGN + DATASOURCE + FROM_WHERE + DATASOURCE_EQ + FROM + "AND " + TO + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForDataSourcePerCampaignWithDateRange(
            @Param("datasource") String datasource,
            @Param("from") String from,
            @Param("to") String to);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ CAMPAIGN + DATASOURCE + FROM_WHERE + CAMPAIGN_EQ + FROM + "AND " + TO + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForCampaignPerDatasourceWithDateRange(
            @Param("campaign") String campaign,
            @Param("from") String from,
            @Param("to") String to);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ CAMPAIGN + DATASOURCE + FROM_WHERE + CAMPAIGN_EQ + "AND " + DATASOURCE_EQ + FROM + "AND " + TO + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForCampaignAndDataSourcePerDatasourceWithDateRange(
            @Param("campaign") String campaign,
            @Param("datasource") String datasource,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);

}
