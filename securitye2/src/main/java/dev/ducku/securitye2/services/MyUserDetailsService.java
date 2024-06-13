package dev.ducku.securitye2.services;

import dev.ducku.securitye2.entity.SecurityUserDetails;
import dev.ducku.securitye2.entity.User;
import dev.ducku.securitye2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(SecurityUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));
    }
}
