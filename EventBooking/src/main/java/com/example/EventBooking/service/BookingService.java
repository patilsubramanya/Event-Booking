package com.example.EventBooking.service;

import com.example.EventBooking.dto.CreateBookingRequest;
import com.example.EventBooking.dto.BookingResponse;
import com.example.EventBooking.exception.BadRequestException;
import com.example.EventBooking.exception.ResourceNotFoundException;
import com.example.EventBooking.model.Booking;
import com.example.EventBooking.model.Event;
import com.example.EventBooking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventService eventService;

    public BookingService(BookingRepository bookingRepository, EventService eventService) {
        this.bookingRepository = bookingRepository;
        this.eventService = eventService;
    }

    public synchronized Booking createBooking(CreateBookingRequest req) {
        if (req.getNumberOfTickets() <= 0) {
            throw new BadRequestException("Tickets must be > 0");
        }

        Event event = eventService.getById(req.getEventId());
        if (event.getAvailableTickets() < req.getNumberOfTickets()) {
            throw new BadRequestException("Not enough tickets available");
        }

        // Deduct tickets
        eventService.addAvailableTickets(event.getId(), -req.getNumberOfTickets());

        Booking booking = new Booking();
        booking.setEventId(event.getId());
        booking.setUserId(req.getUserId());
        booking.setNumberOfTickets(req.getNumberOfTickets());
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public List<BookingResponse> getAllBookings() {
        //System.out.println(String.format("Fetching bookings for username: %s", userId));
        List<Booking> bookings = bookingRepository.findAllByOrderByBookingTimeDesc();

        return bookings.stream().map(b -> {
            Event e = eventService.getById(b.getEventId());
            return new BookingResponse(
                    b.getId(),
                    b.getNumberOfTickets(),
                    b.getBookingTime(),
                    e.getId(),
                    e.getName(),
                    e.getDate(),
                    e.getLocation(),
                    e.getOrganizer(),
                    b.getUserId()
            );
        }).toList();
    }


    public synchronized void cancel(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        // Restore tickets
        eventService.addAvailableTickets(booking.getEventId(), booking.getNumberOfTickets());

        bookingRepository.deleteById(bookingId);
    }
}
