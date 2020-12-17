package com.lb.simpleWarehouse.db;

import lombok.AllArgsConstructor;

import static com.lb.simpleWarehouse.db.CampaignsRepository.*;

public interface QueryEnums {

    String S = "SELECT ";
    String FROM_WHERE = " FROM Campaign c WHERE ";
    String FROM_TABLE = " FROM Campaign c ";
    String FROM_CAMPAIGNS_WHERE = " FROM Campaigns c WHERE ";
    String FROM_CAMPAIGNS_TABLE = " FROM Campaigns c ";
    String GROUP_BY = "GROUP BY c.campaign, c.datasource";
    String SUM_CLICKS = "SUM(c.clicks) as clicks, ";
    String SUM_IMPRESSIONS = "SUM(c.impressions) as impressions, ";
    String CTR = "1.0*SUM(c.clicks)/SUM(c.impressions) as ctr, ";
    String CAMPAIGN = "c.campaign as campaign, ";
    String DATASOURCE = "c.datasource as datasource";
    String DAILY = "c.daily as daily, ";
    String CAMPAIGN_EQ = "c.campaign = :campaign ";
    String DATASOURCE_EQ = "c.datasource = :datasource ";
    String FROM = "c.daily >= :from ";
    String TO = "c.daily <= :to ";

    @AllArgsConstructor
    enum Metric{
        clicks(SUM_CLICKS),
        impressions(SUM_IMPRESSIONS),
        ctr(CTR);

        private final String query;
        public final String query(){
            return query;
        }
    }

    @AllArgsConstructor
    enum Dimension{
        campaign(CAMPAIGN),
        datasource(DATASOURCE + ", "),
        daily(DAILY);

        private final String query;
        public String query(){
            return query;
        }
    }

    @AllArgsConstructor
    enum Filter{
        campaign(CAMPAIGN_EQ),
        datasource(DATASOURCE_EQ),
        from(FROM),
        to(TO);

        private final String query;
        public String query(){
            return query;
        }
    }
}
