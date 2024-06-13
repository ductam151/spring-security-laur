package dev.ducku.securitye3.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.List;

public class CustomAuthentication implements Authentication {

    private final boolean authentication;
    private final String key;

    public CustomAuthentication(boolean authentication, String key) {
        this.authentication = authentication;
        this.key = key;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }



    public String getKey() {
        return key;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
