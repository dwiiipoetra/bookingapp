package com.sinaukoding.eventbookingsystem.model.enums;

import lombok.Getter;

@Getter
public enum TipeUpload {
    BANNER("Banner");

    private final String label;

    TipeUpload(String label) {
        this.label = label;
    }
}
