import React, { useState, useEffect } from "react";
import {
  createTimer,
  getTimer,
  updateTimer,
  deleteTimer,
} from "../services/api";

const TimerForm = ({ onTimerSet }) => {
  const [name, setName] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const [message, setMessage] = useState("");
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchTimer();
  }, []);

  const fetchTimer = async () => {
    try {
      const res = await getTimer();
      setName(res.data.name);
      setTargetDate(res.data.targetDate.substring(0, 16));
      setIsEditing(true);
      onTimerSet(res.data);
    } catch {
      setIsEditing(false);
    }
  };

  const validateInputs = () => {
    if (name.trim().length < 2 || name.trim().length > 32) {
      setMessage("❌ Counter name must be 2–32 characters.");
      return false;
    }
    if (!targetDate || new Date(targetDate) <= new Date()) {
      setMessage("❌ Please select a valid future date/time.");
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateInputs()) return;

    try {
      const payload = {
        name,
        targetDate: new Date(targetDate).toISOString(),
      };

      const res = isEditing
        ? await updateTimer(payload)
        : await createTimer(payload);
      setMessage("✅ Timer saved successfully!");
      onTimerSet(res.data);
      setIsEditing(true);
    } catch (err) {
      setMessage("❌ Failed to save timer.");
    }
  };

  const handleReset = async () => {
    try {
      await deleteTimer();
      setMessage("✅ Timer reset successfully.");
      setName("");
      setTargetDate("");
      setIsEditing(false);
      onTimerSet(null);
    } catch {
      setMessage("❌ Failed to reset timer.");
    }
  };

  return (
    <div className="timer-form">
      <h2>{isEditing ? "Update Countdown Timer" : "Set Countdown Timer"}</h2>

      <form onSubmit={handleSubmit}>
        <label>Counter Name:</label>
        <input
          type="text"
          value={name}
          placeholder="e.g. Launch Event"
          onChange={(e) => setName(e.target.value)}
          required
        />

        <label>Target Date & Time:</label>
        <input
          type="datetime-local"
          value={targetDate}
          onChange={(e) => setTargetDate(e.target.value)}
          required
        />

        <div className="buttons">
          <button type="submit">{isEditing ? "Update" : "Start"}</button>
          {isEditing && (
            <button type="button" className="reset-btn" onClick={handleReset}>
              Reset
            </button>
          )}
        </div>
      </form>

      {message && <p className="message">{message}</p>}
    </div>
  );
};

export default TimerForm;
