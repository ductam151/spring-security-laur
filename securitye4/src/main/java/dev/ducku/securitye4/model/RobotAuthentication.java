package dev.ducku.securitye4.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collections;
import java.util.List;

public class RobotAuthentication implements Authentication {

    private final boolean isAuthenticated;
    private final List<GrantedAuthority> authorities;
    private final String password;

    private RobotAuthentication(String password, List<GrantedAuthority> authorities) {
        this.password = password;
        this.authorities = authorities;
        this.isAuthenticated = password == null;
    }

    public static RobotAuthentication unauthenticated(String password) {
        //do some stuff
        return new RobotAuthentication(password, Collections.emptyList());
    }

    public static RobotAuthentication authenticated() {
        return new RobotAuthentication(null, AuthorityUtils.createAuthorityList("ROLE_user"));
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
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
        return getName();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return "Ms Robot 🤖";
    }
}
