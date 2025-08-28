package com.example.EventBooking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_id", nullable = false)
    private Long eventId;
    @Column(name = "user_name", nullable = false)
    private String userId;
    @Column(name = "tickets_booked", nullable = false)
    private int numberOfTickets;
    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    public Booking() {}

    public Booking(Long id, Long eventId, String userId, int numberOfTickets, LocalDateTime bookingTime) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.numberOfTickets = numberOfTickets;
        this.bookingTime = bookingTime;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
}
