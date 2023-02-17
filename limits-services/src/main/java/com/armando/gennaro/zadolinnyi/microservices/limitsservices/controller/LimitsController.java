package com.armando.gennaro.zadolinnyi.microservices.limitsservices.controller;

import com.armando.gennaro.zadolinnyi.microservices.limitsservices.configuration.BasicConfiguration;
import com.armando.gennaro.zadolinnyi.microservices.limitsservices.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private BasicConfiguration configuration;

    @GetMapping("limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
