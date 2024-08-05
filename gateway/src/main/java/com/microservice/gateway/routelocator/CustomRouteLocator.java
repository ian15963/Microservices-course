package com.microservice.gateway.routelocator;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomRouteLocator {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/microservice/account/**")
                        .filters(f -> f.rewritePath("/microservice/account/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-DateTime", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p.path("/microservice/card/**")
                        .filters(f -> f.rewritePath("/microservice/card/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-DateTime", LocalDateTime.now().toString()))
                        .uri("lb://CARDS"))
                .route(p -> p.path("/microservice/loan/**")
                        .filters(f -> f.rewritePath("/microservice/loan/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-DateTime", LocalDateTime.now().toString()))
                        .uri("lb://LOANS"))
                .build();
    }

}
