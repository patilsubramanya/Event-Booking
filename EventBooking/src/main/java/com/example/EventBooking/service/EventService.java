package com.example.EventBooking.service;

import com.example.EventBooking.dto.CreateEventRequest;
import com.example.EventBooking.exception.ResourceNotFoundException;
import com.example.EventBooking.model.Event;
import com.example.EventBooking.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + id));
    }

    public Event createEvent(CreateEventRequest req) {
        Event e = new Event();
        e.setName(req.getName());
        e.setDate(req.getDate());
        e.setDescription(req.getDescription());
        e.setLocation(req.getLocation());
        e.setOrganizer(req.getOrganizer());
        e.setAvailableTickets(req.getAvailableTickets());
        return eventRepository.save(e);
    }

    // Central method for ticket adjustments
    public void addAvailableTickets(Long eventId, int delta) {
        Event e = getById(eventId);
        int newAvailable = e.getAvailableTickets() + delta;
        if (newAvailable < 0) {
            throw new IllegalStateException("Not enough tickets available");
        }
        e.setAvailableTickets(newAvailable);
        eventRepository.save(e);
    }

    // Booking uses the central ticket update method
    public void bookTickets(Long eventId, String userName, int tickets) {
        if (tickets <= 0) {
            throw new IllegalArgumentException("Number of tickets must be greater than 0");
        }
        addAvailableTickets(eventId, -tickets);
        System.out.println("Booking confirmed for user: " + userName + " | Tickets: " + tickets);
    }
}