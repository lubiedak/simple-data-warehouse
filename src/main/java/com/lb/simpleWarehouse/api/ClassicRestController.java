package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.db.Campaign;
import com.lb.simpleWarehouse.db.CampaignsRepository;
import com.lb.simpleWarehouse.model.TotalClicks;
import com.lb.simpleWarehouse.service.CampaignsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1")
@Tag(name = ClassicRestController.SWAGGER_TAG_CLASSIC_API)
@RequiredArgsConstructor
public class ClassicRestController {

    static final String SWAGGER_TAG_CLASSIC_API = "Classic_API";

    private final CampaignsRepository repository;

    private final CampaignsQueryService queryService;

    @GetMapping(value = "/datasource/{dataSource}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_CLASSIC_API)
    @Operation(summary = "List all campaigns from given datasource")
    public List<Campaign> findAllByDataSource(@PathVariable(value = "dataSource") String dataSource,
                                              @RequestParam(value = "start", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                              @RequestParam(value = "end", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return repository.findAllByDatasource(dataSource);
    }

    @GetMapping(value = "/campaign/{campaignName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_CLASSIC_API)
    @Operation(summary = "List all results for given campaign")
    public Object findAllByCampaignName(@PathVariable(value = "campaignName") String campaignName,
                                                @RequestParam(value = "start", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                                @RequestParam(value = "end", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return repository.findAllByName(campaignName);
    }

    @GetMapping(value = "/campaign/{campaignName}/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_CLASSIC_API)
    @Operation(summary = "List all campaigns from given datasource")
    public Object sumOfImpressionsPerDataSource(@PathVariable(value = "campaignName") String campaign,
                                           @RequestParam(value = "start", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                           @RequestParam(value = "end", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return repository.sumOfClicksAndImpressionsForCampaignPerDatasource(campaign);
    }

    @GetMapping(value = "/datasource/{dataSource}/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_CLASSIC_API)
    @Operation(summary = "List all campaigns from given datasource")
    public Object sumOfClicksPerCampaign(@PathVariable(value = "dataSource") String dataSource,
                               @RequestParam(value = "start", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                               @RequestParam(value = "end", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return repository.sumOfClicksAndImpressionsForDataSourcePerCampaign(dataSource);
    }

    @GetMapping(value = "/datasource/{dataSource}/campaign/{campaignName}/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_CLASSIC_API)
    @Operation(summary = "List all campaigns from given datasource")
    public Object totalsForCampaignAndDatasource(@PathVariable(value = "dataSource") String dataSource,
                                         @PathVariable(value = "campaignName") String campaignName,
                                         @RequestParam(value = "from", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate from,
                                         @RequestParam(value = "to", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate to) {
        return repository.sumOfClicksAndImpressionsForCampaignAndDataSourcePerDatasourceWithDateRange(
                campaignName, dataSource, from, to);
    }
}
