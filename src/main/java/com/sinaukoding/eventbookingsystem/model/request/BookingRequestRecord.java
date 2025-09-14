package com.sinaukoding.eventbookingsystem.model.request;

import com.sinaukoding.eventbookingsystem.model.enums.BookingStatus;
import com.sinaukoding.eventbookingsystem.model.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record BookingRequestRecord(String id,
                                   @NotNull(message = "Booking Date cannot be empty") LocalDate bookingDate,
                                   @NotNull(message = "Booking Status cannot be empty") BookingStatus bookingStatus,
                                   @NotNull(message = "Payment Status cannot be empty") PaymentStatus paymentStatus,
                                   @NotBlank(message = "User ID cannot be empty") String userId,
                                   @NotBlank(message = "Event ID cannot be empty") String eventId
) {}
