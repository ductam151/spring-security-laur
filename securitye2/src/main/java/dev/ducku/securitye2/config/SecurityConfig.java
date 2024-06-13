package dev.ducku.securitye2.config;

import dev.ducku.securitye2.repository.UserRepository;
import dev.ducku.securitye2.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request.requestMatchers("/users").hasAuthority("read"))
                .httpBasic(Customizer.withDefaults())
                .cors(cors -> cors.disable());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
