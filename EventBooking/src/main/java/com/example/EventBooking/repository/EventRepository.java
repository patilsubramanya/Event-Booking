package com.example.EventBooking.repository;

import com.example.EventBooking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
