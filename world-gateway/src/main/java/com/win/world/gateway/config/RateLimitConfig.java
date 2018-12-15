package com.win.world.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RateLimitConfig {

    @Bean
    public KeyResolver apiKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    @Bean
    public KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }
}
