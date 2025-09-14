package com.sinaukoding.eventbookingsystem.model.filter;

import com.sinaukoding.eventbookingsystem.model.enums.Role;
import com.sinaukoding.eventbookingsystem.model.enums.Status;

public record UserFilterRecord(String username,
                               String name,
                               String email,
                               String phone,
                               Status status,
                               Role role) {
}