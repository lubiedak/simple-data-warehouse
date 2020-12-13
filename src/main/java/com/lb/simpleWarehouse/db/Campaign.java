package com.lb.simpleWarehouse.db;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "CAMPAIGNS")
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
}
