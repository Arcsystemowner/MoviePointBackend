package com.moviepoint.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

/**
 * Theater Entity
 * Represents a movie theater with its screens and shows
 */
@Data
@Entity
@Table(name = "theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;
    private String address;
    private Integer totalScreens;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private Set<Screen> screens;
}