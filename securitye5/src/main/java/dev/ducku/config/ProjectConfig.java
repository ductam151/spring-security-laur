package dev.ducku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity(debug = true)
public class ProjectConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                /*authorization always come after authentication*/
                /*1. login with credentials
                 * 2. those credentials will be stored into security context
                 * 3. authorization take permission from security context*/
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/demo").hasAuthority("read");
                    request.anyRequest().authenticated();
                })

                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails u1 = users
                .username("bill")
                .password(passwordEncoder.encode("123456"))
                .authorities("read")
                .build();
        UserDetails u2 = users
                .username("john")
                .password(passwordEncoder.encode("123456"))
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(u1, u2);
    }


//    HandlerMappingIntrospector handlerMappingIntrospector() {
//        return new HandlerMappingIntrospector();
//    }


}
