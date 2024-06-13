package dev.ducku.securitye3.provider;

import dev.ducku.securitye3.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${ducku.authenticaton.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        String headerKey = customAuthentication.getKey();

        if (headerKey.equals(key)) {
            return new CustomAuthentication(true, null);
        }

        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
