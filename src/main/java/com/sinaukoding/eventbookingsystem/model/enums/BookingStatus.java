package com.sinaukoding.eventbookingsystem.model.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled");

    private final String label;

    BookingStatus(String label) {
        this.label = label;
    }
}
