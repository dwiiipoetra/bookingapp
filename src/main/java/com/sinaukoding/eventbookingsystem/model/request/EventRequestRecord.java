package com.sinaukoding.eventbookingsystem.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record EventRequestRecord(String id,
                                 @NotBlank(message = "Title cannot be empty") String title,
                                 @NotBlank(message = "Description cannot be empty") String description,
                                 @NotBlank(message = "Location cannot be empty") String location,
                                 @NotNull(message = "Start Time cannot be empty") LocalDateTime startTime,
                                 @NotNull(message = "End Time cannot be empty") LocalDateTime endTime,
                                 @NotNull(message = "Capacity cannot be empty") Integer capacity,
                                 @NotNull(message = "Price cannot be empty") Double price,
                                 @NotBlank(message = "User ID cannot be empty") String userId,
                                 @NotEmpty(message = "Image cannot be empty") Set<String> listImage
) {}
