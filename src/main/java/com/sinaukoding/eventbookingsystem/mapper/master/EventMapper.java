package com.sinaukoding.eventbookingsystem.mapper.master;

import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.entity.master.EventImage;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventMapper {

    public Event requestToEntity(EventRequestRecord request) {
        Event event = Event.builder()
                .title(request.title())
                .description(request.description())
                .location(request.location())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .capacity(request.capacity())
                .price(request.price())
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