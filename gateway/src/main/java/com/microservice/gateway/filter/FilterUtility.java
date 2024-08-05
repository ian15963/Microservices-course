package com.microservice.gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders){
        if(requestHeaders.get(CORRELATION_ID) != null){
            List<String> requestHeadersList = requestHeaders.get(CORRELATION_ID);
            return requestHeadersList.stream().findFirst().get();
        }
        return null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange webExchange, String name, String value){
        return webExchange.mutate().request(webExchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange webExchange, String correlationId){
        return this.setRequestHeader(webExchange, CORRELATION_ID, correlationId);
    }

}
