package com.lb.simpleWarehouse.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CampaignsRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByCampaign(String campaign);
    List<Campaign> findAllByDatasource(String datasource);

    String S = "SELECT ";
    String SUM_CLICKS = "SUM(c.clicks) as clicks, ";
    String SUM_IMPRESSIONS = "SUM(c.impressions) as impressions, ";
    String CTR = "1.0*SUM(c.clicks)/SUM(c.impressions) as ctr, ";
    String REGULAR_DIMENSIONS = "c.campaign as campaign, c.datasource as datasource ";
    String FROM = "FROM Campaign c WHERE ";
    String CAMPAIGN_EQ = "c.campaign = :campaign_name ";
    String DATASOURCE_EQ = "c.datasource = :datasource ";
    String DATE_RANGE = "AND c.daily >= :from AND c.daily <= :to ";
    String GROUP_BY = "group by c.campaign, c.datasource";

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ REGULAR_DIMENSIONS + FROM + DATASOURCE_EQ + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForDataSourcePerCampaign(
            @Param("datasource") String datasource);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ REGULAR_DIMENSIONS + FROM + CAMPAIGN_EQ + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForCampaignPerDatasource(
            @Param("campaign_name") String campaignName);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ REGULAR_DIMENSIONS + FROM + DATASOURCE_EQ + DATE_RANGE + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForDataSourcePerCampaignWithDateRange(
            @Param("datasource") String datasource,
            @Param("from") String from,
            @Param("to") String to);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ REGULAR_DIMENSIONS + FROM + CAMPAIGN_EQ + DATE_RANGE + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForCampaignPerDatasourceWithDateRange(
            @Param("campaign_name") String campaignName,
            @Param("from") String from,
            @Param("to") String to);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS +CTR+ REGULAR_DIMENSIONS + FROM + CAMPAIGN_EQ + "AND " + DATASOURCE_EQ + DATE_RANGE + GROUP_BY)
    List<Map<String, String>> sumOfClicksAndImpressionsForCampaignAndDataSourcePerDatasourceWithDateRange(
            @Param("campaign_name") String campaignName,
            @Param("datasource") String datasource,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);

}
