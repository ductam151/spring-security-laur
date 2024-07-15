package dev.ducku.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public class CustomJwtConverter implements Converter<Jwt, CustomJwtAuthenticationToken> {
    @Override
    public CustomJwtAuthenticationToken convert(Jwt source) {
        List<String> authorities = (List<String>) source.getClaims().get("authorities");
        CustomJwtAuthenticationToken customJwtAuthenticationToken = new CustomJwtAuthenticationToken(source, authorities.stream().map(SimpleGrantedAuthority::new).toList());
        return customJwtAuthenticationToken;
    }
}
