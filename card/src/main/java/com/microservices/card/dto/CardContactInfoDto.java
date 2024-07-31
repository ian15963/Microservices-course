package com.microservices.card.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("cards")
public class CardContactInfoDto {

    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;

}
