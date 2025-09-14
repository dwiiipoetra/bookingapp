package com.sinaukoding.eventbookingsystem.service.master;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.BookingFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    void add(BookingRequestRecord request);

    void edit(BookingRequestRecord request);

    Page<SimpleMap> findAll(BookingFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}