package dev.ducku.securitye2.controllers;

import dev.ducku.securitye2.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity findUserByUsername(@Param("username") String username, Principal principal) {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        user.getAuthorities().forEach(System.out::println);
        return ResponseEntity.ok(user);
    }
}
