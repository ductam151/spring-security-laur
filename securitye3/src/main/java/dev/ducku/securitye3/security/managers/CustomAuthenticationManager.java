package dev.ducku.securitye3.security.managers;

import dev.ducku.securitye3.provider.CustomAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider provider;

    public CustomAuthenticationManager(CustomAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports(authentication.getClass())) {
            return provider.authenticate(authentication);
        }
        throw new BadCredentialsException("Oh, No!, throwing bad credentials");
    }
}
