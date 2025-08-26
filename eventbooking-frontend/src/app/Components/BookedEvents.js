'use client';

import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from '../Components/Navbar';
import api from '../api';

const BookedEvents = () => {
  const [bookedEvents, setBooked] = useState([]);

  const fetchBookedEvents = async () => {
    try {
      const res = await api.get('/bookings');
      setBooked(res.data);
    } catch (err) {
      console.error('Failed to fetch booked events', err);
    }
  };

  useEffect(() => {
    fetchBookedEvents();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this booking?')) return;

    try {
      await api.delete(`/bookings/${id}`);
      // Remove deleted booking from state
      //setBooked(bookedEvents => bookedEvents.filter((b) => b.id !== id));
      setBooked(prevBooked => prevBooked.filter((b) => b.id !== id));
    //   alert(`Successfully booked ${tickets} ticket(s)!`);
      alert(`Successfully deleted the tickets!`);
    } catch (err) {
      console.error('Failed to delete booking', err);
      alert('Failed to delete booking');
    }
  };

  return (
    <>
      <Navbar />
      <div className="container" style={{ marginTop: '5rem' }}>
        <h2 className="mb-4">Booked Events</h2>
        {bookedEvents.length === 0 ? (
          <p>No booked events found</p>
        ) : (
          <table className="table">
            <thead>
                <tr>
                    <th>Event Name</th>
                    <th>Date</th>
                    <th>Location</th>
                    <th>Organizer</th>
                    <th>User</th>
                    <th>Tickets Booked</th>
                    <th>Action</th> {/* New column for delete */}
                </tr>
            </thead>
            <tbody>
              {bookedEvents.map((b) => (
                <tr key={b.id}>
                <td>{b.eventName}</td>
                <td>{b.eventDate}</td>
                <td>{b.eventLocation}</td>
                <td>{b.eventOrganizer}</td>
                <td>{b.userId}</td>
                <td>{b.numberOfTickets}</td>
                <td>
                <button
                className="btn btn-danger btn-sm"
                onClick={() => handleDelete(b.bookingId)}
                >
                Delete
                </button>
                </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
};

export default BookedEvents;
