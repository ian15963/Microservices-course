package com.microservices.account.service;

import com.microservices.account.dto.CustomerDto;

public interface ICustomerService {

    CustomerDto fetchCustomerDtoDetails(String correlationId, String mobileNumber);

}
