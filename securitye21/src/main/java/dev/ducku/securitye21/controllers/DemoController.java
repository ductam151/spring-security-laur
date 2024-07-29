package dev.ducku.securitye21.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")
    public String demo() {
        return "Demo 👋👋";
    }


    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('write')")
    public String hello() {
        return "Hello 👋👋";
    }
}
