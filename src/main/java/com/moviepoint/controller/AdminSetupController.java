package com.moviepoint.controller;

import com.moviepoint.entity.User;
import com.moviepoint.entity.UserRole;
import com.moviepoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin-setup")
public class AdminSetupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "MY_SUPER_SECRET_KEY_123";

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestHeader("X-ADMIN-SETUP-KEY") String secretKey, @RequestBody User user) {
        if (!SECRET_KEY.equals(secretKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Secret Key");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(com.moviepoint.entity.UserRole.ROLE_ADMIN);
        userRepository.save(user);

        return ResponseEntity.ok("Admin created successfully");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editAdmin(@RequestHeader("X-ADMIN-SETUP-KEY") String secretKey, @RequestBody User user) {
        if (!SECRET_KEY.equals(secretKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Secret Key");
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!com.moviepoint.entity.UserRole.ROLE_ADMIN.equals(existingUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can only edit admins via this API");
        }

        // Update fields
        if (user.getName() != null)
            existingUser.setName(user.getName());
        if (user.getPhoneNumber() != null)
            existingUser.setPhoneNumber(user.getPhoneNumber());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(existingUser);

        return ResponseEntity.ok("Admin updated successfully");
    }
}
