package com.moviepoint.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Booking Entity
 * Represents a movie ticket booking with payment information
 */
@Data
// @Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Column(nullable = false)
    private Integer numberOfSeats;

    private Double totalAmount;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String paymentId;
    private String selectedSeats; // Stored as comma-separated seat numbers

    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}