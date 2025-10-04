package com.moviepoint.dto;

import lombok.Data;
import com.moviepoint.model.User.UserRole;

/**
 * DTO for user registration requests
 */
@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private UserRole role;
}