package com.example.EventBooking.controller;

import com.example.EventBooking.dto.CreateEventRequest;
import com.example.EventBooking.model.Event;
import com.example.EventBooking.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> all() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Event one(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Event> create(@Valid @RequestBody CreateEventRequest req) {
        Event e = eventService.createEvent(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(e);
    }

    // New booking endpoint
    @PostMapping("/book/{id}")
    public ResponseEntity<?> bookTickets(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        try {
            String userName = (String) body.get("user_name");
            Integer tickets = (Integer) body.get("tickets");

            if (userName == null || tickets == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields"));
            }

            eventService.bookTickets(id, userName, tickets);
            return ResponseEntity.ok(Map.of("message", "Booking successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
