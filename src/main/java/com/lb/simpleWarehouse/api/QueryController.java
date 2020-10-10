package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.api.model.TotalClicks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v2")
public class QueryController {
    public static final List<String> METRICS = Arrays.asList("clicks", "impressions");
    public static final List<String> DIMENSIONS = Arrays.asList("campaign", "datasource", "start", "end");
    public static final List<String> FILTERS_KEYS = DIMENSIONS;


    @GetMapping(value = "/query/metrics/{metrics}/dimensions/{dimensions}/filters/{filters}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalClicks campaignClicks(@PathVariable(value = "metrics") String metrics,
                                      @PathVariable(value = "dimensions") String dimensions,
                                      @PathVariable(value = "filters") String filters) {
        return null;
    }
}
