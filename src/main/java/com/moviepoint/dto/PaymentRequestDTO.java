package com.moviepoint.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * DTO for payment request
 */
@Data
public class PaymentRequestDTO {
    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;  // e.g., "CREDIT_CARD", "UPI", etc.

    @NotBlank(message = "Payment details are required")
    private String paymentDetails; // JSON string containing payment method specific details
}