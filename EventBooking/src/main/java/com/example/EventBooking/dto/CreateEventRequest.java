package com.example.EventBooking.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CreateEventRequest {
    @NotBlank private String name;
    @NotNull  private LocalDate date;
    @NotBlank private String description;
    @NotBlank private String location;
    @NotBlank private String organizer;
    @Positive  private int availableTickets;

    // getters & setters
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public LocalDate getDate() { return date; } public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; } public void setLocation(String location) { this.location = location; }
    public String getOrganizer() { return organizer; } public void setOrganizer(String organizer) { this.organizer = organizer; }
    public int getAvailableTickets() { return availableTickets; } public void setAvailableTickets(int availableTickets) { this.availableTickets = availableTickets; }
}
