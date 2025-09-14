package com.sinaukoding.eventbookingsystem.model.filter;

import com.sinaukoding.eventbookingsystem.model.enums.BookingStatus;
import com.sinaukoding.eventbookingsystem.model.enums.PaymentStatus;

import java.time.LocalDate;

public record BookingFilterRecord(
    LocalDate bookingDate,
    BookingStatus bookingStatus,
    PaymentStatus paymentStatus
) {
}