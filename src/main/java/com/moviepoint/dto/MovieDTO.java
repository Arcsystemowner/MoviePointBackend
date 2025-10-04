package com.moviepoint.dto;

import lombok.Data;
import com.moviepoint.model.Movie.MovieStatus;
import java.util.List;

/**
 * Data Transfer Object for Movie entity
 * Used for API requests and responses
 */
@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> genre; // Changed to List to match frontend
    private String language;
    private Integer duration;
    private Double rating;
    private String posterUrl;
    private MovieStatus status;
}