package com.moviepoint.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShowRequest {
    private Long movieId;
    private Long theaterId;
    private LocalDateTime showTime;
    private Double basePrice;
}