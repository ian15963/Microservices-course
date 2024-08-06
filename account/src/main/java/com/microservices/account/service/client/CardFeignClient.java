package com.microservices.account.service.client;

import com.microservices.account.dto.CardDto;
import com.microservices.account.service.fallback.FallBackCardsFeignClient;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cards", fallback = FallBackCardsFeignClient.class)
public interface CardFeignClient {

    @GetMapping(value = "/api/fetch", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CardDto> fetchCard(@RequestHeader("eazybank-correlation-id") String correlationId,
                                      @RequestParam
                                      @Pattern(regexp = "(^$|[0-9]{10})", message = "CardNumber must be 10 digits")
                                      String mobileNumber);

}
