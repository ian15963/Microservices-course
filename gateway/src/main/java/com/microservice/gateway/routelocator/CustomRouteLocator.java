package com.microservice.gateway.routelocator;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CustomRouteLocator {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/microservice/account/**")
                        .filters(f -> f.rewritePath("/microservice/account/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-DateTime", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("accountCircuitBreaker")
                                        .setFallbackUri("forward:/contact-support")))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p.path("/microservice/card/**")
                        .filters(f -> f.rewritePath("/microservice/card/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-DateTime", LocalDateTime.now().toString())
                                .retry(retry -> retry.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000),2, true)))
                        .uri("lb://CARDS"))
                .route(p -> p.path("/microservice/loan/**")
                        .filters(f -> f.rewritePath("/microservice/loan/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-DateTime", LocalDateTime.now().toString())
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())))
                        .uri("lb://LOANS"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
    }

    @Bean
    public RedisRateLimiter redisRateLimiter(){
        return new RedisRateLimiter(1,1,1);
    }

    @Bean
    public KeyResolver userKeyResolver(){
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }
}
