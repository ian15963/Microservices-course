package com.microservices.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("loans")
public class LoansContactInfoDto {

    String message;
    Map<String, String> contactDetails;
    List<String> onCallSuport;
}
