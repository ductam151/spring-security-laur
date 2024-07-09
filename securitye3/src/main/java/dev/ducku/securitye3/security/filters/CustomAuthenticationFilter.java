package dev.ducku.securitye3.security.filters;

import dev.ducku.securitye3.security.authentication.CustomAuthentication;
import dev.ducku.securitye3.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component                                      /*use this superclass to ensure that this filter only be called once*/
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager customAuthenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain

        String key = String.valueOf(request.getHeader("key"));
        /*1. */CustomAuthentication customAuthentication = new CustomAuthentication(false, key);
        Authentication authenticate = customAuthenticationManager.authenticate(customAuthentication);/*2. */
        if(authenticate.isAuthenticated()) { /*3. */
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            filterChain.doFilter(request, response);
        }
    }
}
