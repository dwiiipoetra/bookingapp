package com.sinaukoding.eventbookingsystem.mapper.management_user;

import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User requestToEntity(UserRequestRecord request) {
        return User.builder()
            .username(request.username().toUpperCase())
            .name(request.name())
            .email(request.email().toLowerCase())
            .phone(request.phone().toLowerCase())
            .password(request.password())
            .status(request.status())
            .role(request.role())
            .build();
    }
}

