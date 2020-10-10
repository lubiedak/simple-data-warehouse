package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.api.model.BasicResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1")
public class ApiDefinitionController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<BasicResponse> base() {
        BasicResponse apiDefinition = new BasicResponse();
        apiDefinition.add(linkTo(methodOn(ClassicRestController.class)
                .dataSource("dataSource", LocalDate.now(), LocalDate.now())).withSelfRel());
        apiDefinition.addEndpointDescription("GET: Retrieve totalClicks for a given dataSource in given timeRange");
        apiDefinition.add(linkTo(methodOn(ClassicRestController.class)
                .dataSource("dataSource", LocalDate.now(), LocalDate.now())).withSelfRel());
        apiDefinition.addEndpointDescription("GET: Retrieve totalClicks for a given dataSource in given timeRange");
        return new ResponseEntity<>(apiDefinition, OK);
    }
}
