package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class EndpointListingConfig {

    @Autowired
    @Qualifier("requestMappingHandlerMapping")
    private RequestMappingHandlerMapping handlerMapping;

    @Bean
    public Map<String, String> getAllEndpoints() {
        return handlerMapping.getHandlerMethods().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),

                        entry -> entry.getValue().toString()
                ));
    }
}

