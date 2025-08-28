package com.example.EventBooking.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events") // table name in MySQL
public class Event {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    private String name;
    private LocalDate date;
    private String description;
    private String location;
    private String organizer;
    @Column(name = "available_tickets")
    private int availableTickets;

    public Event() {}

    public Event(Long id, String name, LocalDate date, String description,
                 String location, String organizer, int availableTickets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.location = location;
        this.organizer = organizer;
        this.availableTickets = availableTickets;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }
    public int getAvailableTickets() { return availableTickets; }
    public void setAvailableTickets(int availableTickets) { this.availableTickets = availableTickets; }
}