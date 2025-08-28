import React from 'react';
import Link from 'next/link';
import 'bootstrap/dist/css/bootstrap.min.css';

const Navbar = () => {
  return (
    <nav className="navbar navbar-dark bg-dark bg-opacity-75 fixed-top">
      <div className="container d-flex justify-content-between align-items-center">
        {/* Title */}
        <Link className="navbar-brand fw-bold text-light" href="/">
          EventBooking
        </Link>

        {/* Navigation Links */}
        <ul className="navbar-nav d-flex flex-row gap-4">
          <li className="nav-item">
            <Link href="/" className="nav-link text-light">
              Home
            </Link>
          </li>
          <li className="nav-item">
            <Link href="/BookedEvents" className="nav-link text-light">
              View Booked Events
            </Link>
          </li>
          <li className="nav-item">
            <Link href="/AddEvent" className="nav-link text-light">
              Add New Event
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;

