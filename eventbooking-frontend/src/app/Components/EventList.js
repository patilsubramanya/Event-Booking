"use client";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

export default function EventList() {
  const router = useRouter();
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  // Fetch all events
  const fetchEvents = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/events");
      if (!res.ok) throw new Error("Failed to fetch events");
      const data = await res.json();
      setEvents(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);

  // Navigate to booking page
  const handleBook = (eventId) => {
    router.push(`/book/${eventId}`);
  };

  if (loading) return <p>Loading events...</p>;

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Event List</h2>
      {events.length === 0 ? (
        <p>No events found.</p>
      ) : (
        <div className="row">
          {events.map((event) => (
            <div key={event.id} className="col-md-4 mb-4">
              <div className="card h-100 shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">{event.name}</h5>
                  <p className="card-text">
                    <strong>Location:</strong> {event.location} <br />
                    <strong>Date:</strong> {event.date} <br />
                    <strong>Available Tickets:</strong> {event.availableTickets}
                  </p>
                  <button
                    onClick={() => handleBook(event.id)}
                    className="btn btn-primary w-100"
                  >
                    Book Tickets
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
