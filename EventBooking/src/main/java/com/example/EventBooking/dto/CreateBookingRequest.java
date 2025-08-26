package com.example.EventBooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateBookingRequest {
    @NotNull  private Long eventId;
    @NotBlank private String userId;
    @Positive private int numberOfTickets;

    public Long getEventId() { return eventId; } public void setEventId(Long eventId) { this.eventId = eventId; }
    public String getUserId() { return userId; } public void setUserId(String userId) { this.userId = userId; }
    public int getNumberOfTickets() { return numberOfTickets; } public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
}
