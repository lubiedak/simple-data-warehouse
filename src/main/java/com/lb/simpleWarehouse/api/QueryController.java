package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.db.QueryEnums.*;
import com.lb.simpleWarehouse.service.CampaignsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v2")
@Tag(name = QueryController.SWAGGER_TAG_QUERY_CONTROLLER)
@RequiredArgsConstructor
public class QueryController {

    static final String SWAGGER_TAG_QUERY_CONTROLLER = "Query_API";

    private final CampaignsQueryService queryService;

    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_QUERY_CONTROLLER)
    @Operation(summary = "List available roles for specified subscription")
    public Object campaignClicks(@RequestParam(value = "metrics") List<Metric> metrics,
                                 @RequestParam(value = "dimensions", required = false) List<Dimension> dimensions,
                                 @RequestBody(required = false) Map<Filter,String> filters) {
        return queryService.query(metrics, dimensions, filters);
    }

    @GetMapping(value = "/query/help", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = SWAGGER_TAG_QUERY_CONTROLLER)
    @Operation(summary = "List available params for query")
    public Object queryHelp() {
        var params = new HashMap<String, Object>();
        params.put("metrics", Metric.values());
        params.put("dimensions", Dimension.values());
        params.put("filters(body)", Filter.values());
        return params;
    }
}
