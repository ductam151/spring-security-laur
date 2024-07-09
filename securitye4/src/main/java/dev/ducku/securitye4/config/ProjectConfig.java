package dev.ducku.securitye4.config;

import dev.ducku.securitye4.security.filter.ApiKeyAuthenticationFilter;
import dev.ducku.securitye4.security.provider.KuAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {
    @Value("${api.key}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new ApiKeyAuthenticationFilter(key), BasicAuthenticationFilter.class)
                .authenticationProvider(new KuAuthenticationProvider())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().build();
    }
}
