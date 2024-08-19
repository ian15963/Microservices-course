package com.microservice.message.functions;

import com.microservice.message.dto.AccountMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class)

    @Bean
    public Function<AccountMessageDto, AccountMessageDto> email(){
        return account -> {
            logger.info("Sending email with the details: " + account.toString());
          return account;
        };
    }

    @Bean
    public Function<AccountMessageDto, Long> sms(){
        return accountDto -> {
            logger.info("Sending sms with the details: " accountDto.toString());
            return accountDto.accountNumber();
        };
    }

}
