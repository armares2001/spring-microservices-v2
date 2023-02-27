package com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.controller;

import com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService service;

    /*currency-exchange/from/USD/to/INR*/
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        log.info("retrieveExchangeValue => exchange");
        return service.findByFromAndTo(from,to);
    }
}
