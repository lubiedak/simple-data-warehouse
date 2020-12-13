package com.lb.simpleWarehouse.loader;

import com.lb.simpleWarehouse.db.Campaign;
import com.lb.simpleWarehouse.db.CampaignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignsDataLoader {

    private final CampaignsRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadObjects(){
        var loader = new CsvLoader();
        var campaignsData = loader.loadObjectList(CampaignInput.class, "db/input/campaigns_data.csv");
        var campaigns = campaignsData.stream().map(Campaign::from).collect(Collectors.toList());
        repository.saveAll(campaigns);
    }
}
