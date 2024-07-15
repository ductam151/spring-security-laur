package dev.ducku.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwkUri}")
    private String jwkUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(resourceConfigure -> {
            resourceConfigure.jwt(j -> j.jwkSetUri(jwkUri).jwtAuthenticationConverter(new CustomJwtConverter()));
        });

        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        return http.build();
    }
}
