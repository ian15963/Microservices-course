package com.microservices.account.controller.impl;

import com.microservices.account.controller.ICustomerController;
import com.microservices.account.dto.CustomerDto;
import com.microservices.account.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerControllerImpl implements ICustomerController {

    private final ICustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerControllerImpl.class);

    @Override
    public ResponseEntity<CustomerDto> fetAllCustomerDetails(String correlationId, String mobileNumber) {
        CustomerDto dto = customerService.fetchCustomerDtoDetails(correlationId, mobileNumber);
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.ok(dto);
    }
}
