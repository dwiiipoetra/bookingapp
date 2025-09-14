package com.sinaukoding.eventbookingsystem.service.master.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.builder.CustomSpecification;
import com.sinaukoding.eventbookingsystem.builder.MultipleCriteria;
import com.sinaukoding.eventbookingsystem.builder.SearchCriteria;
import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.entity.master.EventImage;
import com.sinaukoding.eventbookingsystem.mapper.master.EventMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.master.EventRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.master.EventService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ValidatorService validatorService;
    private final EventMapper eventMapper;

    @Override
    public void add(EventRequestRecord request) {
//        try {
            log.trace("Go to the add event data menu");
            log.debug("Request data event: {}", request);

            // validator mandatory
            validatorService.validator(request);

            if (request.capacity() < 0) {
                log.warn("Capacity cannot be less than 0");
            }

            var event = eventMapper.requestToEntity(request);
            eventRepository.save(event);

            log.info("Event {} successfully added", request.title());
            log.trace("Add event data successfully and complete");
//        } catch (Exception e) {
//            log.error("Add event data failed: {}", e.getMessage());
//        }
    }

    @Override
    public void edit(EventRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var eventExisting = eventRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Event not found"));
        var event = eventMapper.requestToEntity(request);
        event.setId(eventExisting.getId());
        eventRepository.save(event);
    }

    @Override
    public Page<SimpleMap> findAll(EventFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Event> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("title", filterRequest.title(), builder);
        FilterUtil.builderConditionNotBlankLike("location", filterRequest.location(), builder);
//        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);

        if (filterRequest.bottomPrice() != null && filterRequest.topPrice() != null) {
            builder.with(MultipleCriteria.builder().criterias(
                    SearchCriteria.OPERATOR_AND,
                    SearchCriteria.of("price", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.bottomPrice()),
                    SearchCriteria.of("price", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.topPrice())
            ));
        } else if (filterRequest.topPrice() != null) {
            builder.with("price", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.topPrice());
        } else if (filterRequest.bottomPrice() != null) {
            builder.with("price", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.bottomPrice());
        }

        Page<Event> listEvent = eventRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listEvent.stream().map(event -> {
            SimpleMap data = new SimpleMap();
            data.put("id", event.getId());
            data.put("title", event.getTitle());
            data.put("description", event.getDescription());
            data.put("location", event.getLocation());
            data.put("capacity", event.getCapacity());
            data.put("price", event.getPrice());
            data.put("createdDate", event.getCreatedDate());
            data.put("modifiedDate", event.getModifiedDate());
//            data.put("listImage", event.getListImage().stream().map(EventImage::getPath).collect(Collectors.toSet()));
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listEvent.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));

        SimpleMap data = new SimpleMap();
        data.put("id", event.getId());
        data.put("title", event.getTitle());
        data.put("description", event.getDescription());
        data.put("location", event.getLocation());
        data.put("capacity", event.getCapacity());
        data.put("price", event.getPrice());
        data.put("createdDate", event.getCreatedDate());
        data.put("modifiedDate", event.getModifiedDate());
//        data.put("listImage", event.getListImage().stream().map(EventImage::getPath).collect(Collectors.toSet()));
        return data;
    }

}