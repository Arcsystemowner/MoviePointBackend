package com.moviepoint.dto;

import lombok.Data;
import com.moviepoint.model.Booking.BookingStatus;
import java.time.LocalDateTime;

/**
 * DTO for booking response data
 */
@Data
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private Long showId;
    private String movieTitle;
    private String theaterName;
    private String screenName;
    private LocalDateTime showTime;
    private Integer numberOfSeats;
    private String selectedSeats;
    private Double totalAmount;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private String paymentId;
}