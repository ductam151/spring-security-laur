package dev.ducku.securitye18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/demo")
    public String demo() {
        System.out.println("😀😃");
        return "demo";
    }

    @PostMapping("/smth")
    public String smth() {
        System.out.println("😀😃");
        return "smth";
    }
}
