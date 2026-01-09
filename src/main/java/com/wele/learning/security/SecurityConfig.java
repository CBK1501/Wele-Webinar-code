package com.wele.learning.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder(
            @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkSetUri) {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            KeycloakRoleConverter keycloakRoleConverter
    ) throws Exception {

        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/actuator/**",
//                    "/graphql/**",
//                    "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**"
//                ).permitAll()
//                .anyRequest().authenticated()
                            .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder(
                        null // ignored, bean already exists (Spring will inject it normally)
                    ))
                    .jwtAuthenticationConverter(jwtAuthConverter)
                )
            );

        return http.build();
    }
}
