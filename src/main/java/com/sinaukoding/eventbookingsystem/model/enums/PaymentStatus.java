package com.sinaukoding.eventbookingsystem.model.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    UNPAID ("Unpaid"),
    PAID("Paid"),
    REFUNDED("Refunded");

    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }
}
