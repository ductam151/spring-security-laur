package dev.ducku.securitye4.security.manager;

import dev.ducku.securitye4.security.provider.ApiKeyAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationManger implements AuthenticationManager {

    private final String key;

    public CustomAuthenticationManger(String key) {
        this.key = key;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationProvider provider = new ApiKeyAuthenticationProvider(key);

        if (provider.supports(authentication.getClass())) {
            return provider.authenticate(authentication);
        }
        return authentication;
    }
}
