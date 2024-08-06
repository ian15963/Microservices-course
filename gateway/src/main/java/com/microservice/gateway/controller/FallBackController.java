package com.microservice.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @GetMapping("/contact-support")
    public Mono<String> fallBackMessage(){
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }

}
