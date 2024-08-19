package com.microservice.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security){
        return security
                .authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("microservice/account/**").hasRole("ACCOUNT")
                        .pathMatchers("microservice/card/**").hasRole("CARD")
                        .pathMatchers("microservice/loan/**").hasRole("LOAN"))
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtConverter())))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

}
