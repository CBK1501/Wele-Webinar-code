package com.wele.learning.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

//@Component
public class TokenValidationFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(TokenValidationFilter.class);

    private final ReactiveJwtDecoder jwtDecoder;

    // Explicit constructor - NO Lombok
    public TokenValidationFilter(ReactiveJwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.debug("Auth Headers  "+authHeader);
        // If no token -> allow request (other filters will handle authentication)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        String token = authHeader.substring(7);

        // Decode JWT using JWKS-based decoder (NO HTTP CALLS to Keycloak)
        return jwtDecoder.decode(token)
                .flatMap((Jwt jwt) -> {
                    // Store decoded JWT (optional but useful for debugging)
                    exchange.getAttributes().put("jwt", jwt);
                    return chain.filter(exchange);
                })
                .onErrorResume(ex -> {
                    log.error("Token validation failed", ex);
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }
}
