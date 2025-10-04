package com.moviepoint.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Column(name = "show_time", nullable = false)
    private LocalDateTime showTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "base_price")
    private Double basePrice;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();
}