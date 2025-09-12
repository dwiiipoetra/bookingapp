package com.sinaukoding.eventbookingsystem.model.filter;

public record EventFilterRecord (
    String title,
    String description,
    String location,
    Integer capacity,
    Double topPrice,
    Double bottomPrice
) {
}