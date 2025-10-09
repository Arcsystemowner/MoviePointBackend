package com.moviepoint.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

/**
 * Movie Entity
 * Represents a movie with its details and shows
 */
@Data
// @Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private String genre;
    private String language;
    private Integer duration; // in minutes
    private Double rating;
    private String posterUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @OneToMany(mappedBy = "movie")
    private Set<Show> shows;

    public enum MovieStatus {
        NOW_SHOWING,
        COMING_SOON,
        NOT_SHOWING
    }
}