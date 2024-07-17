package dev.ducku.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomJwtConverter customJwtConverter;

    @Value("${jwkUri}")
    private String jwkUri;

    @Value("${opaque.resource.server.client.id}")
    private String opaqueClientId;

    @Value("${opaque.resource.server.client.secret}")
    private String opaqueClientSecret;

    @Value("${opaque.resource.server.introspect.uri}")
    private String opaqueIntrospectUri;


    public SecurityConfig(CustomJwtConverter customJwtConverter) {
        this.customJwtConverter = customJwtConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(resourceConfigure -> {
            resourceConfigure.jwt(j -> j.jwkSetUri(jwkUri).jwtAuthenticationConverter(customJwtConverter)); //JWT TOKEN 🎫

            /*resourceConfigure.opaqueToken(o -> o.introspectionUri(opaqueIntrospectUri)  //OPAQUE TOKEN 🎟️
                    .introspectionClientCredentials(opaqueClientId, opaqueClientSecret));
*/
        });

        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        return http.build();
    }
}
