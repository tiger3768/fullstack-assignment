# Fullstack Engineer Online Assessment

## Task Overview

**Build a Countdown Timer Web App**

Create a web application that allows users to:

- Set a **counter name** and a **target date/time** for a countdown timer via the frontend.
- Save this information to the backend and persist it in a database.
- Once the timer is set and saved successfully, the frontend displays a live countdown (days, hours, minutes, seconds) until the target date/time.
- The user should also be able to **update** or **reset** the countdown from the frontend.

---

## Requirements

- **Frontend:** Use React (preferred) or any modern frontend framework.
- **Backend:** Use Node.js (Express), Python (FastAPI/Django), or Java (Spring Boot).
- **Database:** Persist the timer data (counter name, target date/time) using PostgreSQL, MongoDB, or SQLite/JSON file.
- **API:** RESTful endpoints between frontend and backend.
- **Input Validation:**
  - **Counter name** must not be empty and should be a reasonable length (e.g., 2–32 characters).
  - **Date/time** must be a valid future datetime.
- **User Feedback:** Show clear error or success messages for actions.
- **Responsiveness:** UI should be usable on desktop and mobile.
- **README:** Provide clear run/setup instructions.

---

## Deliverables

- Source code (GitHub repo or zip)
- Simple README with clear instructions to run both frontend and backend

---

## Minimum Features

### Frontend

- Form to input **counter name** and **target date/time**
- Button to **set/start** the countdown (saves to backend)
- Button to **update** the countdown (edit and save new counter name or target date/time)
- Button to **reset** the countdown (removes or resets timer on backend)
- Display live countdown (days, hours, minutes, seconds) after setting
- Show error or success messages for actions

### Backend

- `POST /timer` — Save a new timer (name + target date/time)
- `GET /timer` — Get the current timer (name + target date/time)
- `PUT /timer` — **Update** the timer (edit counter name or target date/time)
- `DELETE /timer` — Reset/delete the timer

- Persist data in a database

---

## Optional (Bonus)

- **Support multiple timers:** Allow user to create, view, and delete multiple named timers.
- **JWT Authentication:** Secure timer endpoints.
- **Monitoring & Logging:** Log timer changes, errors, API usage.
- **Responsive UI:** Use Tailwind CSS or similar.
- **Dockerize** the application.
- **Advanced error handling:** Graceful handling of API/database failures.

---

## Time Limit

**1.5 hours**

---

## Evaluation Criteria

- Functionality: Does the core workflow work?
- Code quality: Clean, readable, and maintainable code
- Proper API usage and frontend/backend separation
- Input validation and user experience
- Clear setup and run instructions

---

**Good luck!**
