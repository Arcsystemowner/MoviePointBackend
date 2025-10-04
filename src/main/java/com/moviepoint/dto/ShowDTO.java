package com.moviepoint.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * DTO for show data
 */
@Data
public class ShowDTO {
    private Long id;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotNull(message = "Screen ID is required")
    private Long screenId;

    @NotNull(message = "Show time is required")
    @Future(message = "Show time must be in the future")
    private LocalDateTime showTime;

    @NotNull(message = "Base price is required")
    @Min(value = 0, message = "Base price cannot be negative")
    private Double basePrice;

    // Additional fields for response
    private String movieTitle;
    private String screenName;
    private String theaterName;
    private Integer availableSeats;
}