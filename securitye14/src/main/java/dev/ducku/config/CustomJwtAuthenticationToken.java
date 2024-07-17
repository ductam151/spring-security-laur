package dev.ducku.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {

    private String customMessage;

    private String priority;

    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String priority) {
        super(jwt, authorities);
        this.priority = priority;
        customMessage = "🐱 🐶";
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public String getPriority() {
        return priority;
    }


}
