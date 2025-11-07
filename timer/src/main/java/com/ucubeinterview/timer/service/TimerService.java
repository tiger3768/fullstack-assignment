package com.ucubeinterview.timer.service;

import org.springframework.stereotype.Service;

import com.ucubeinterview.timer.exception.ResourceNotFoundException;
import com.ucubeinterview.timer.model.Timer;
import com.ucubeinterview.timer.repository.TimerRepository;

import java.time.Instant;
import java.util.List;

@Service
public class TimerService {
    private final TimerRepository timerRepository;

    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public Timer getTimer() {
        List<Timer> timers = timerRepository.findAll();
        if (timers.isEmpty()) {
            throw new ResourceNotFoundException("No timer found.");
        }
        return timers.get(0);
    }

    public Timer createTimer(Timer timer) {
        // Clear old timers — single timer logic
        timerRepository.deleteAll();
        timer.setCreatedAt(Instant.now());
        timer.setUpdatedAt(Instant.now());
        return timerRepository.save(timer);
    }

    public Timer updateTimer(Timer timer) {
        Timer existing = getTimer();
        existing.setName(timer.getName());
        existing.setTargetDate(timer.getTargetDate());
        existing.setUpdatedAt(Instant.now());
        return timerRepository.save(existing);
    }

    public void deleteTimer() {
        timerRepository.deleteAll();
    }
}
