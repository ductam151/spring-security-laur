package dev.ducku.securitye3.config;

import dev.ducku.securitye3.security.filters.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class MyAppConfig {
    private final CustomAuthenticationFilter customAuthenticationFilter;

    public MyAppConfig(CustomAuthenticationFilter customAuthenticationFilter) {
        this.customAuthenticationFilter = customAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
}
