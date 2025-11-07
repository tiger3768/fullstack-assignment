import React, { useEffect, useState } from "react";

const Countdown = ({ timer }) => {
  const [remaining, setRemaining] = useState(
    getTimeRemaining(timer?.targetDate)
  );

  useEffect(() => {
    if (!timer) return;
    const interval = setInterval(() => {
      setRemaining(getTimeRemaining(timer.targetDate));
    }, 1000);
    return () => clearInterval(interval);
  }, [timer]);

  if (!timer) return null;

  if (remaining.total <= 0)
    return <h3 className="finished">🎉 {timer.name} has arrived!</h3>;

  return (
    <div className="countdown">
      <h2>{timer.name}</h2>
      <div className="time-box">
        <div>
          <span>{remaining.days}</span>
          <p>Days</p>
        </div>
        <div>
          <span>{remaining.hours}</span>
          <p>Hours</p>
        </div>
        <div>
          <span>{remaining.minutes}</span>
          <p>Minutes</p>
        </div>
        <div>
          <span>{remaining.seconds}</span>
          <p>Seconds</p>
        </div>
      </div>
    </div>
  );
};

function getTimeRemaining(targetDate) {
  if (!targetDate) return {};
  const total = Date.parse(targetDate) - Date.now();
  const seconds = Math.floor((total / 1000) % 60);
  const minutes = Math.floor((total / 1000 / 60) % 60);
  const hours = Math.floor((total / (1000 * 60 * 60)) % 24);
  const days = Math.floor(total / (1000 * 60 * 60 * 24));
  return { total, days, hours, minutes, seconds };
}

export default Countdown;
