package com.moviepoint.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
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

    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    private Double amount;

    @Column(name = "seat_numbers")
    private String seatNumbers;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}