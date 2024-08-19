package com.microservices.account.dto;

public record AccountMessageDto(Integer accountNumber, String name, String email, String mobileNumber) {
}
