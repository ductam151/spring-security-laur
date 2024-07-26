package dev.ducku.securitye18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*http.csrf(csrf -> csrf.disable()); DON'T DO THAT IN REAL WORLD SCENARIO*/

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/smth"));
        return http.build();
    }
}
