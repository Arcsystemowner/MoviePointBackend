package com.moviepoint.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

/**
 * User Entity
 * Represents a user in the system with authentication and role information
 */
@Data
// @Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings;

    public enum UserRole {
        USER,
        ADMIN,
        THEATER_OWNER
    }
}