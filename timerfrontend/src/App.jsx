import React, { useState } from "react";
import TimerForm from "./components/TimerForm";
import Countdown from "./components/Countdown";
import "./styles/App.css";

function App() {
  const [timer, setTimer] = useState(null);

  return (
    <div className="app">
      <h1>⏰ Countdown Timer</h1>
      <TimerForm onTimerSet={setTimer} />
      <Countdown timer={timer} />
    </div>
  );
}

export default App;
