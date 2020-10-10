package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.api.model.TotalClicks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v2")
public class QueryController {
    public static final List<String> METRICS = Arrays.asList("clicks", "impressions");
    public static final List<String> DIMENSIONS = Arrays.asList("campaign", "datasource", "start", "end");
    public static final List<String> FILTERS = DIMENSIONS;


    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalClicks campaignClicks(@RequestParam(value = "metrics") List<String> metrics,
                                      @RequestParam(value = "dimensions") List<String> dimensions,
                                      @RequestParam(value = "filters") List<String> filters) {
        return null;
    }
}
