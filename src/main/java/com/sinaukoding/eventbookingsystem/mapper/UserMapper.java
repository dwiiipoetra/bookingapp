package com.sinaukoding.eventbookingsystem.mapper;

import com.sinaukoding.eventbookingsystem.entity.User;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User requestToEntity(UserRequestRecord request) {
        return User.builder()
            .name(request.name().toUpperCase())
            .email(request.email().toLowerCase())
            .phone(request.phone().toLowerCase())
            .password(request.password())
            .status(request.status())
            .role(request.role())
            .build();
    }
}

