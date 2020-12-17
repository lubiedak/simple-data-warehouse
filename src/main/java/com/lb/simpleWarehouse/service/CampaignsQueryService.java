package com.lb.simpleWarehouse.service;

import com.lb.simpleWarehouse.db.CampaignsRepository;
import com.lb.simpleWarehouse.db.QueryEnums.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.lb.simpleWarehouse.api.QueryController.*;
import static com.lb.simpleWarehouse.db.CampaignsRepository.*;
import static com.lb.simpleWarehouse.db.QueryEnums.*;

@Service
@RequiredArgsConstructor
public class CampaignsQueryService {

    private final CampaignsRepository repository;
    @PersistenceContext
    private final EntityManager em;

    public Object query(List<Metric> metrics, List<Dimension> dimensions, Map<Filter, String> filters){

        em.createNativeQuery("");
        return null;
    }

    String prepareSelect(List<String> metrics, List<String> dimensions){
        return S + "";
    }
}
