package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/endpoints")
public class EndpointController {

    private final Map<String, String> endpoints;

    @Autowired
    public EndpointController(Map<String, String> endpoints) {
        this.endpoints = endpoints;
    }

    @GetMapping
    public Map<String, String> getEndpoints() {
        return endpoints;
    }
}
