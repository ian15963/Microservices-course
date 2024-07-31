package com.microservices.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("accounts")
public class AccountContactInfoDto {

    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;
}
