package com.lb.simpleWarehouse.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasicResponse extends RepresentationModel<BasicResponse> {
    List<String> endpoints = new ArrayList<>();

    public void addEndpointDescription(String description){
        endpoints.add(description);
    }
}
