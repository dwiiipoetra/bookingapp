package com.sinaukoding.eventbookingsystem.model.request;

import com.sinaukoding.eventbookingsystem.model.enums.Role;
import com.sinaukoding.eventbookingsystem.model.enums.Status;

public record UserRequestRecord(String id,
                                String username,
                                String name,
                                String email,
                                String phone,
                                String password,
                                Status status,
                                Role role,
                                String token) {
}