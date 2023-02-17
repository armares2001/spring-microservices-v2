package com.armando.gennaro.zadolinnyi.microservices.currencyconversionservice.service;

import com.armando.gennaro.zadolinnyi.microservices.currencyconversionservice.dto.CurrencyConversionResponseDto;
import com.armando.gennaro.zadolinnyi.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class CurrencyConversionService {

    @Autowired
    private CurrencyExchangeProxy proxy;
    @Autowired
    private RestTemplate template;
    public CurrencyConversionResponseDto calculateCurrencyConversion(String from, String to, BigDecimal q) {
        HashMap<String,String> uriVariables=new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        CurrencyConversionResponseDto resp=template.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionResponseDto.class,uriVariables).getBody();
        if (resp==null){
            throw new RuntimeException("not found");
        }
        resp.setQuantity(q);
        resp.setTotalCalculatedAmount(resp.getQuantity().multiply(resp.getConversionMultiple()));
        return resp;
    }

    public CurrencyConversionResponseDto calculateCurrencyConversionFeign(String from, String to, BigDecimal q) {

        CurrencyConversionResponseDto resp=proxy.retrieveExchangeValue(from,to);
        if (resp==null){
            throw new RuntimeException("not found");
        }
        resp.setQuantity(q);
        resp.setTotalCalculatedAmount(resp.getQuantity().multiply(resp.getConversionMultiple()));
        return resp;
    }
}
