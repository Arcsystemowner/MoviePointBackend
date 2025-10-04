package com.moviepoint.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * DTO for creating a new booking
 */
@Data
public class BookingRequestDTO {
    @NotNull(message = "Show ID is required")
    private Long showId;

    @NotNull(message = "Number of seats is required")
    @Min(value = 1, message = "Must book at least one seat")
    @Max(value = 10, message = "Cannot book more than 10 seats at once")
    private Integer numberOfSeats;

    @NotEmpty(message = "Selected seats are required")
    private String selectedSeats; // Comma-separated seat numbers
}