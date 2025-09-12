package com.sinaukoding.eventbookingsystem.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Admin"),
    ORGANIZER("Organizer"),
    PARTICIPANT("Participant");

    private final String label;

    Role(String label) {
        this.label = label;
    }
}
