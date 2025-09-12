package com.sinaukoding.eventbookingsystem.service.master;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    void add(EventRequestRecord request);

    void edit(EventRequestRecord request);

    Page<SimpleMap> findAll(EventFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}