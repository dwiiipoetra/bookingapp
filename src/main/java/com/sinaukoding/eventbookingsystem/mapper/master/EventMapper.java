package com.sinaukoding.eventbookingsystem.mapper.master;

import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.entity.master.EventImage;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.management_user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventMapper {
    private final UserRepository userRepository;

    public Event requestToEntity(EventRequestRecord request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = Event.builder()
                .title(request.title())
                .description(request.description())
                .location(request.location())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .capacity(request.capacity())
                .price(request.price())
                .user(user)
                .build();

        event.setListImage(request.listImage().stream()
                .map(path -> EventImage.builder()
                        .path(path)
                        .event(event)
                        .build())
                .collect(Collectors.toSet()));

        return event;
    }

}