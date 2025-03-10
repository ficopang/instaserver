package com.fico.instaserver.service;

import com.fico.instaserver.model.AuthRequest;
import com.fico.instaserver.model.AuthResponse;
import com.fico.instaserver.model.User;
import com.fico.instaserver.repository.UserRepository;
import com.fico.instaserver.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse authenticate(AuthRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user);
                return new AuthResponse(user.getUsername(), token);
            }
        }
        throw new RuntimeException("Invalid username or password");
    }
}
