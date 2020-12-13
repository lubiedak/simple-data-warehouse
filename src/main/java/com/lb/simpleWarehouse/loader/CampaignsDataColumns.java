package com.lb.simpleWarehouse.loader;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CampaignsDataColumns {
    DATASOURCE("Datasource"),
    CAMPAIGN("Campaign"),
    DAILY("Daily"),
    CLICKS("Clicks"),
    IMPRESSIONS("Impressions");

    @Getter
    private String value;
}
