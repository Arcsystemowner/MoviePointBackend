package com.moviepoint.dto;

import lombok.Data;

/**
 * DTO for login requests
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}