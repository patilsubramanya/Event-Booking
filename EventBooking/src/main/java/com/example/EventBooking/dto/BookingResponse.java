package com.example.EventBooking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingResponse {
    private Long bookingId;
    private int numberOfTickets;
    private LocalDateTime bookingTime;
    private Long eventId;
    private String eventName;
    private LocalDate eventDate;
    private String eventLocation;
    private String eventOrganizer;
    private String userId;

    public BookingResponse(Long bookingId, int numberOfTickets, LocalDateTime bookingTime,
                           Long eventId, String eventName, LocalDate eventDate,
                           String eventLocation, String eventOrganizer, String  userId) {
        this.bookingId = bookingId;
        this.numberOfTickets = numberOfTickets;
        this.bookingTime = bookingTime;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventOrganizer = eventOrganizer;
        this.userId = userId;
    }

    // getters
    public Long getBookingId() { return bookingId; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public Long getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public LocalDate getEventDate() { return eventDate; }
    public String getEventLocation() { return eventLocation; }
    public String getEventOrganizer() { return eventOrganizer; }
    public String getUserId() { return userId; }
}
