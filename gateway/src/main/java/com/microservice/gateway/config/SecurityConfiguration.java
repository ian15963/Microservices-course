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

    @Autowired
    private JwtConverter jwtConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security){
        return security
                .authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("microservice/account/**").authenticated()
                        .pathMatchers("microservice/card/**").authenticated()
                        .pathMatchers("microservice/loan/**").authenticated())
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

}
