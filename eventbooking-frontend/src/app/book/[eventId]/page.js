"use client";
import { useState, useEffect } from "react";
import { useRouter, useParams } from "next/navigation";

export default function BookEvent() {
  const router = useRouter();
  const params = useParams();
  const { eventId } = params;

  const [event, setEvent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [tickets, setTickets] = useState(1);
  const [userName, setUserName] = useState("");

  // Fetch event details
  useEffect(() => {
    async function fetchEvent() {
      try {
        const res = await fetch(`http://localhost:8080/api/events/${eventId}`);
        const data = await res.json();
        setEvent(data);
      } catch (err) {
        console.error("Error fetching event:", err);
      } finally {
        setLoading(false);
      }
    }
    fetchEvent();
  }, [eventId]);

  // Handle booking
  const handleBooking = async () => {
    if (!event) return alert("Event not loaded yet. Please wait.");
    if (!userName.trim()) return alert("Please enter your name");

    const maxTickets = event.availableTickets;
    if (tickets < 1 || tickets > maxTickets)
      return alert(`Please select between 1 and ${maxTickets} tickets.`);

    try {
      const res = await fetch(`http://localhost:8080/api/bookings`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          eventId: event.id,           // matches backend
          userId: userName,           // matches backend
          numberOfTickets: tickets    // matches backend
        }),
      });

      const data = await res.json();

      if (!res.ok) {
        alert(`Booking failed: ${data.message || "Unknown error"}`);
        return;
      }

      alert(`Successfully booked ${tickets} ticket(s)!`);
      router.push(`/BookedEvents?userId=${encodeURIComponent(userName)}`); // pass userId for filtering
    } catch (err) {
      console.error("Booking error:", err);
      alert("Booking failed! Check console for details.");
    }
  };

  if (loading) return <p>Loading event...</p>;
  if (!event) return <p>Event not found.</p>;

  return (
    <div className="container mt-4">
      <h2>Book Tickets for {event.name}</h2>
      <p>
        <strong>Location:</strong> {event.location} <br />
        <strong>Date:</strong> {event.date} <br />
        <strong>Available Tickets:</strong> {event.availableTickets}
      </p>

      <div className="mb-3">
        <label className="form-label">Your Name:</label>
        <input
          type="text"
          className="form-control"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label className="form-label">Number of Tickets:</label>
        <input
          type="number"
          className="form-control"
          min="1"
          max={event.availableTickets}
          value={tickets}
          onChange={(e) => setTickets(Number(e.target.value))}
        />
      </div>

      <button className="btn btn-success" onClick={handleBooking}>
        Confirm Booking
      </button>
    </div>
  );
}
