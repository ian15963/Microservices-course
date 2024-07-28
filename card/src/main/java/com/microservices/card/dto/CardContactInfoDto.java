package com.microservices.card.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("cards")
public record CardContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
