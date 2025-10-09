package com.moviepoint.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

/**
 * Screen Entity
 * Represents a screen in a theater with its seating arrangement
 */
@Data
// @Entity
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "screen")
    private Set<Show> shows;
}