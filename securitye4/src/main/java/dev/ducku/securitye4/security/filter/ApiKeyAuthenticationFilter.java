package dev.ducku.securitye4.security.filter;

import dev.ducku.securitye4.model.ApiKeyAuthentication;
import dev.ducku.securitye4.security.manager.CustomAuthenticationManger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {


    private final String apiKey;

    public ApiKeyAuthenticationFilter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestKey = request.getHeader("x-api-key");
        ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(requestKey);

        CustomAuthenticationManger authenticationManger = new CustomAuthenticationManger(apiKey);

        if ("null".equals(requestKey) || requestKey == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = authenticationManger.authenticate(apiKeyAuthentication);
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                return;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Type", "text/plain;charset=utf-8");
                response.getWriter().println("You are not authenticated ⛔");

            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/plain;charset=utf-8");
            response.getWriter().println("You are not authenticated ⛔");
        }


    }
}
