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
        String port=env.getProperty("local.server.port");
        String host=env.getProperty("HOSTNAME");
        String version="v11";
        exchange.setEnvironment(port+" "+version+" "+host);
        return exchange;
    }
}
