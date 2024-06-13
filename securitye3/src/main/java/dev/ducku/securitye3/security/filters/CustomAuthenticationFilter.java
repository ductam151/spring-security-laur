package dev.ducku.securitye3.security.filters;

import dev.ducku.securitye3.security.authentication.CustomAuthentication;
import dev.ducku.securitye3.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager customAuthenticationManager;

    public CustomAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain

        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication customAuthentication = new CustomAuthentication(false, key);
        Authentication authenticate = customAuthenticationManager.authenticate(customAuthentication);
        if(authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            filterChain.doFilter(request, response);
        }
    }
}
