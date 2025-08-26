package com.example.EventBooking.controller;

import com.example.EventBooking.dto.CreateBookingRequest;
import com.example.EventBooking.dto.BookingResponse;
import com.example.EventBooking.model.Booking;
import com.example.EventBooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> create(@Valid @RequestBody CreateBookingRequest req) {
        Booking b = bookingService.createBooking(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    // Match frontend's POST /api/book/{eventId}
    @PostMapping("/book/{eventId}")
    public ResponseEntity<Booking> createFromEvent(
            @PathVariable Long eventId,
            @RequestBody Booking bookingRequest) {

        CreateBookingRequest req = new CreateBookingRequest();
        req.setEventId(eventId);
        req.setUserId(bookingRequest.getUserId());
        req.setNumberOfTickets(bookingRequest.getNumberOfTickets());

        Booking b = bookingService.createBooking(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @GetMapping
    public List<BookingResponse> getAllbookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        bookingService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
