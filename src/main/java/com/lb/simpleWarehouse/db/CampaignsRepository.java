package com.lb.simpleWarehouse.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignsRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByName(String name);
}
