package com.armando.gennaro.zadolinnyi.microservices.currencyconversionservice.controller;

import com.armando.gennaro.zadolinnyi.microservices.currencyconversionservice.dto.CurrencyConversionResponseDto;
import com.armando.gennaro.zadolinnyi.microservices.currencyconversionservice.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService service;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{q}")
    public CurrencyConversionResponseDto calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal q){
        return service.calculateCurrencyConversion(from,to,q);
    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{q}")
    public CurrencyConversionResponseDto calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal q){
        return service.calculateCurrencyConversionFeign(from,to,q);
    }
}
