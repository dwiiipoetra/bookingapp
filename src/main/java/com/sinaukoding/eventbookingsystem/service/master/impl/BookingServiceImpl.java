package com.sinaukoding.eventbookingsystem.service.master.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.entity.master.Booking;
import com.sinaukoding.eventbookingsystem.mapper.master.BookingMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.BookingFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.master.BookingRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.master.BookingService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ValidatorService validatorService;
    private final BookingMapper bookingMapper;

    @Override
    public void add(BookingRequestRecord request) {
//        try {
            log.trace("Go to the add event data menu");
            log.debug("Request data event: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var booking = bookingMapper.requestToEntity(request);
            bookingRepository.save(booking);

            log.info("Booking {} successfully added", request.bookingDate());
            log.trace("Add booking data successfully and complete");
//        } catch (Exception e) {
//            log.error("Add booking data failed: {}", e.getMessage());
//        }
    }

    @Override
    public void edit(BookingRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var bookingExisting = bookingRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Booking not found"));
        var booking = bookingMapper.requestToEntity(request);
        booking.setId(bookingExisting.getId());
        bookingRepository.save(booking);
    }

    @Override
    public Page<SimpleMap> findAll(BookingFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Booking> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotNullEqual("bookingDate", filterRequest.bookingDate(), builder);
        FilterUtil.builderConditionNotNullEqual("bookingStatus", filterRequest.bookingStatus(), builder);
        FilterUtil.builderConditionNotNullEqual("paymentStatus", filterRequest.paymentStatus(), builder);

        Page<Booking> listBooking = bookingRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listBooking.stream().map(booking -> {
            SimpleMap data = new SimpleMap();
            data.put("id", booking.getId());
            data.put("bookingDate", booking.getBookingDate());
            data.put("bookingStatus", booking.getBookingStatus());
            data.put("paymentStatus", booking.getPaymentStatus());
            data.put("createdDate", booking.getCreatedDate());
            data.put("modifiedDate", booking.getModifiedDate());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listBooking.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));

        SimpleMap data = new SimpleMap();
        data.put("id", booking.getId());
        data.put("bookingDate", booking.getBookingDate());
        data.put("bookingStatus", booking.getBookingStatus());
        data.put("paymentStatus", booking.getPaymentStatus());
        data.put("createdDate", booking.getCreatedDate());
        data.put("modifiedDate", booking.getModifiedDate());
        return data;
    }

}