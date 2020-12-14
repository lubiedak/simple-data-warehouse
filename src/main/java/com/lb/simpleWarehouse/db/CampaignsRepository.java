package com.lb.simpleWarehouse.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public interface CampaignsRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByName(String name);
    List<Campaign> findAllByDatasource(String datasource);

    String S = "SELECT ";
    String SUM_CLICKS = "SUM(c.clicks) as clicks, ";
    String SUM_IMPRESSIONS = "SUM(c.impressions) as impressions, ";
    String REGULAR_DIMENSIONS = "c.name as campaign_name, c.datasource as datasource ";
    String FROM = "FROM Campaign c WHERE ";
    String CAMPAIGN_EQ = "c.name = :campaign_name ";
    String DATASOURCE_EQ = "c.datasource = :datasource ";
    String DATE_RANGE = "AND c.daily >= :from AND c.daily <= :to ";
    String GROUP_BY = "group by c.name, c.datasource";

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS + REGULAR_DIMENSIONS + FROM + DATASOURCE_EQ + GROUP_BY)
    List<TreeMap<String, String>> sumOfClicksAndImpressionsForDataSourcePerCampaign(
            @Param("datasource") String datasource);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS + REGULAR_DIMENSIONS + FROM + CAMPAIGN_EQ + GROUP_BY)
    List<TreeMap<String, String>> sumOfClicksAndImpressionsForCampaignPerDatasource(
            @Param("campaign_name") String campaignName);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS + REGULAR_DIMENSIONS + FROM + DATASOURCE_EQ + DATE_RANGE + GROUP_BY)
    List<TreeMap<String, String>> sumOfClicksAndImpressionsForDataSourcePerCampaignWithDateRange(
            @Param("datasource") String datasource,
            @Param("from") String from,
            @Param("to") String to);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS + REGULAR_DIMENSIONS + FROM + CAMPAIGN_EQ + DATE_RANGE + GROUP_BY)
    List<TreeMap<String, String>> sumOfClicksAndImpressionsForCampaignPerDatasourceWithDateRange(
            @Param("campaign_name") String campaignName,
            @Param("from") String from,
            @Param("to") String to);

    @Query(S + SUM_CLICKS + SUM_IMPRESSIONS + REGULAR_DIMENSIONS + FROM + CAMPAIGN_EQ + "AND " + DATASOURCE_EQ + DATE_RANGE + GROUP_BY)
    List<TreeMap<String, String>> sumOfClicksAndImpressionsForCampaignAndDataSourcePerDatasourceWithDateRange(
            @Param("campaign_name") String campaignName,
            @Param("datasource") String datasource,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);

}
