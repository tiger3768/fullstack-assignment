package com.ucubeinterview.timer.service;

import org.springframework.stereotype.Service;

import com.ucubeinterview.timer.exception.ResourceNotFoundException;
import com.ucubeinterview.timer.model.Timer;
import com.ucubeinterview.timer.repository.TimerRepository;

import java.time.Instant;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimerService {
    private final TimerRepository timerRepository;

    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public Timer getTimer() {
        log.info("Fetching timer from database...");
        List<Timer> timers = timerRepository.findAll();
        if (timers.isEmpty()) {
            log.warn("No timer found in database.");
            throw new ResourceNotFoundException("No timer found.");
        }
        return timers.get(0);
    }

    public Timer createTimer(Timer timer) {
        log.info("Creating new timer: {}", timer.getName());
        timerRepository.deleteAll();
        timer.setCreatedAt(Instant.now());
        timer.setUpdatedAt(Instant.now());
        Timer saved = timerRepository.save(timer);
        log.debug("Timer saved successfully: {}", saved);
        return saved;
    }

    public Timer updateTimer(Timer timer) {
        log.info("Updating timer to new target date: {}", timer.getTargetDate());
        Timer existing = getTimer();
        existing.setName(timer.getName());
        existing.setTargetDate(timer.getTargetDate());
        existing.setUpdatedAt(Instant.now());
        Timer updated = timerRepository.save(existing);
        log.debug("Timer updated successfully: {}", updated);
        return updated;
    }

    public void deleteTimer() {
        log.info("Deleting all timers from database...");
        timerRepository.deleteAll();
        log.warn("All timers deleted successfully.");
    }
}
