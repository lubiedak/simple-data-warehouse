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
@Table(name = "CAMPAIGNS")
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "campaign")
    private String campaign;
    @Column
    private String datasource;
    @Column
    private LocalDate daily;
    @Column
    private int clicks;
    @Column
    private int impressions;

    public static Campaign from(CampaignInput input) {
        return builder()
                .campaign(input.getCampaign())
                .datasource(input.getDatasource())
                .daily(input.getDaily())
                .clicks(input.getClicks())
                .impressions(input.getImpressions())
                .build();
    }
}
