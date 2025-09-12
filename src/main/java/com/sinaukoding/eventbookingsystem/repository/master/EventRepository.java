package com.sinaukoding.eventbookingsystem.repository.master;

import com.sinaukoding.eventbookingsystem.entity.master.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, String>, JpaSpecificationExecutor<Event> {
}
