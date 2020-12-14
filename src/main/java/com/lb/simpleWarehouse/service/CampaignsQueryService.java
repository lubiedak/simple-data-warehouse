package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.CampaignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignsQueryService {

    private final CampaignsRepository repository;


}
