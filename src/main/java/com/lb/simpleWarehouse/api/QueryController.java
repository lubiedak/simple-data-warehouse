package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.model.TotalClicks;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v2")
@Tag(name = QueryController.SWAGGER_TAG_QUERY_CONTROLLER)
public class QueryController {

    static final String SWAGGER_TAG_QUERY_CONTROLLER = "Query_API";

    public static final List<String> METRICS = Arrays.asList("clicks", "impressions");
    public static final List<String> DIMENSIONS = Arrays.asList("campaign", "datasource", "start", "end");
    public static final List<String> FILTERS_KEYS = DIMENSIONS;


    @GetMapping(value = "/query/metrics/{metrics}/dimensions/{dimensions}/filters/{filters}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_QUERY_CONTROLLER)
    @Operation(summary = "List available roles for specified subscription")
    public TotalClicks campaignClicks(@PathVariable(value = "metrics") String metrics,
                                      @PathVariable(value = "dimensions") String dimensions,
                                      @PathVariable(value = "filters") String filters) {
        return null;
    }
}
