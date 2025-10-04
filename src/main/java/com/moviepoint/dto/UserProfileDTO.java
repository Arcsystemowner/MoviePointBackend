package com.moviepoint.dto;

import lombok.Data;
import com.moviepoint.model.User.UserRole;

/**
 * DTO for user profile data
 */
@Data
public class UserProfileDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private UserRole role;
    // Exclude password for security
}