package dev.ducku.securitye4.security.provider;

import dev.ducku.securitye4.model.ApiKeyAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private final String key;

    public ApiKeyAuthenticationProvider(String key) {
        this.key = key;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiKeyAuthentication apiKeyAuthentication = (ApiKeyAuthentication) authentication;

        if (key.equals(apiKeyAuthentication.getKey())) {
            apiKeyAuthentication.setAuthenticated(true);
            return apiKeyAuthentication;
        }

        throw new BadCredentialsException("Invalid key");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
