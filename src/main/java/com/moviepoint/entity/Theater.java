package com.moviepoint.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private String city;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private Set<Show> shows = new HashSet<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private Set<Seat> seats = new HashSet<>();
}