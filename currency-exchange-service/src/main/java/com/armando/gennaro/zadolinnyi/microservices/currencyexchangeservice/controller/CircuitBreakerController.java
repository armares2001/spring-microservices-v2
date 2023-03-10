package com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {

    @GetMapping("sample-api")
    //@Retry(name="sample-api",fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name="sample-api",fallbackMethod = "hardcodedResponse")
    public String getSampleApi(){
        log.info("sample api call received");
        ResponseEntity<String> forEntity=new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);
        return forEntity.getBody();
    }

    @GetMapping("limit-rate")
    @RateLimiter(name="limit-rate")
    public String getLimitRate(){
        return "limit-rate";
    }

    private String hardcodedResponse(Exception ex){
        return "fallback-response";
    }
}
