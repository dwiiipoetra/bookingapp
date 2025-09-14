package com.sinaukoding.eventbookingsystem.model.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecord(@NotBlank String name,
                                 @NotBlank String password) {
}