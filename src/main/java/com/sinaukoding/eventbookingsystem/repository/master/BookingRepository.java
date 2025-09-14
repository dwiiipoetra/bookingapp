package com.sinaukoding.eventbookingsystem.repository.master;

import com.sinaukoding.eventbookingsystem.entity.master.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingRepository extends JpaRepository<Booking, String>, JpaSpecificationExecutor<Booking> {
}
