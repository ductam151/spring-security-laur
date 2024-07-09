package dev.ducku.securitye6.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails bill = users.username("bill")
                .password(passwordEncoder.encode("12345"))
                .authorities("read", "write")
                .build();

        UserDetails john = users.username("john")
                .password(passwordEncoder.encode("12345"))
                .authorities("delete", "update")
                .build();
        return new InMemoryUserDetailsManager(bill, john);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.POST).hasAnyAuthority("write");
                    request.requestMatchers(HttpMethod.GET, "/demo/**").hasAuthority("read");
                    request.anyRequest().authenticated();
                })
                .csrf(csrf-> csrf.disable())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
