package com.lb.simpleWarehouse.db;

import com.lb.simpleWarehouse.loader.CampaignInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "CAMPAIGNS",
        indexes = {@Index(name = "idx_campaign", columnList = "name, datasource, daily, clicks, impressions")})
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private String datasource;
    @Column
    private LocalDate daily;
    @Column
    private int clicks;
    @Column
    private int impressions;

    public static Campaign from(CampaignInput input){
        return builder()
                .name(input.getCampaign())
                .datasource(input.getDatasource())
                .daily(input.getDaily())
                .clicks(input.getClicks())
                .impressions(input.getImpressions())
                .build();
    }
}
