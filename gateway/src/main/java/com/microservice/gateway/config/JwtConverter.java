package com.microservice.gateway.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JwtConverter implements Converter<Jwt, Mono<JwtAuthenticationToken>> {

    @Override
    public Mono<JwtAuthenticationToken> convert(Jwt jwt) {
        Map<String, List<String>> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = realmAccess.get("roles");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(!roles.isEmpty()){
            authorities = roles.stream().map(authority -> new SimpleGrantedAuthority("ROLE_%s".formatted(authority))).toList();
        }

        return Mono.just(new JwtAuthenticationToken(jwt, authorities));
    }
}
