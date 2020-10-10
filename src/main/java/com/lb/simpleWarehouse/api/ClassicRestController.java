package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.api.model.TotalClicks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;



@RestController
@RequestMapping("api/v1")
public class ClassicRestController {

    @GetMapping(value = "/datasource/{dataSource}/clicks", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalClicks dataSourceClicks(@PathVariable(value = "dataSource") String dataSource,
                                @RequestParam(value = "start") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                @RequestParam(value = "end") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return new TotalClicks(dataSource, start, end, 2);
    }

    @GetMapping(value = "/datasource/{dataSource}/impressions", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalClicks dataSourceImpressions(@PathVariable(value = "dataSource") String dataSource,
                                        @RequestParam(value = "start") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                        @RequestParam(value = "end") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return new TotalClicks(dataSource, start, end, 2);
    }

    @GetMapping(value = "/campaign/{campaign}/clicks", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalClicks campaignClicks(@PathVariable(value = "campaign") String campaign,
                                  @RequestParam(value = "start") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                  @RequestParam(value = "end") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return new TotalClicks(campaign, start, end, 2);
    }

    @GetMapping(value = "/campaign/{campaign}/impressions", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalClicks campaignImpressions(@PathVariable(value = "campaign") String campaign,
                                      @RequestParam(value = "start") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate start,
                                      @RequestParam(value = "end") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate end) {
        return new TotalClicks(campaign, start, end, 2);
    }
}
