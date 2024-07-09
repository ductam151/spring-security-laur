package dev.ducku.securitye4.controller;

import dev.ducku.securitye4.model.ApiKeyAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "this is demo 🧐🧐🧐\n";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
