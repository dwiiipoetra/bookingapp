package com.sinaukoding.eventbookingsystem.mapper.master;

import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import com.sinaukoding.eventbookingsystem.entity.master.Booking;
import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.management_user.UserRepository;
import com.sinaukoding.eventbookingsystem.repository.master.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public Booking requestToEntity(BookingRequestRecord request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return Booking.builder()
                .bookingDate(request.bookingDate())
                .bookingStatus(request.bookingStatus())
                .paymentStatus(request.paymentStatus())
                .user(user)
                .event(event)
                .build();
    }

}