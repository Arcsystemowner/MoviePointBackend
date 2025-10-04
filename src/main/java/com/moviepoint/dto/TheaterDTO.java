package com.moviepoint.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * DTO for theater data
 */
@Data
public class TheaterDTO {
    private Long id;

    @NotBlank(message = "Theater name is required")
    @Size(min = 3, max = 100, message = "Theater name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 1, message = "Theater must have at least one screen")
    private Integer totalScreens;
}