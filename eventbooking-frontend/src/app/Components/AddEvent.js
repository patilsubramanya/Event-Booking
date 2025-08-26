"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";

export default function AddEvent() {
    const router = useRouter();
  const [formData, setFormData] = useState({
    name: "",
    date: "",
    description: "",
    location: "",
    organizer: "",
    availableTickets: ""
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Send data to backend
    const res = await fetch("http://localhost:8080/api/events", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        ...formData,
        availableTickets: parseInt(formData.availableTickets, 10)
      })
    });

    if (res.ok) {
      alert("Event added successfully!");
      router.push("/");
      setFormData({
        name: "",
        date: "",
        description: "",
        location: "",
        organizer: "",
        availableTickets: ""
      });
    } else {
      alert("Error adding event");
    }
  };

  return (
    <div>
      <h2>Add Event</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Event Name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="date"
          name="date"
          value={formData.date}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="description"
          placeholder="Description"
          value={formData.description}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="location"
          placeholder="Location"
          value={formData.location}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="organizer"
          placeholder="Organizer"
          value={formData.organizer}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="availableTickets"
          placeholder="Available Tickets"
          value={formData.availableTickets}
          onChange={handleChange}
          required
        />
        <button type="submit">Add Event</button>
      </form>
    </div>
  );
}
