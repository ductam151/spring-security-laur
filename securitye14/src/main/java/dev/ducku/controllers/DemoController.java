package dev.ducku.controllers;

import dev.ducku.config.CustomJwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public Authentication demo(Authentication authentication) {
       return authentication;
    }
}
