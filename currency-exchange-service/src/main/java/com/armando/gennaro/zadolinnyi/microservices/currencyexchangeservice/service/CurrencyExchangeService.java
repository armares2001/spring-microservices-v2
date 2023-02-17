package com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.service;

import com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.armando.gennaro.zadolinnyi.microservices.currencyexchangeservice.jpa.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    private Environment env;
    @Autowired
    private CurrencyExchangeRepository repository;

    public CurrencyExchange findByFromAndTo(String from, String to) {
        CurrencyExchange exchange=repository.findByFromAndTo(from,to);
        if (exchange == null) {
            throw new RuntimeException("exchange not found");
        }
        exchange.setEnvironment(env.getProperty("local.server.port"));
        return exchange;
    }
}
