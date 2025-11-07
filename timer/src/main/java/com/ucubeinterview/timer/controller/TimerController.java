package com.ucubeinterview.timer.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.ucubeinterview.timer.model.Timer;
import com.ucubeinterview.timer.service.TimerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequestMapping("/api/timer")
@CrossOrigin(origins = "*")
public class TimerController {
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping
    public ResponseEntity<Timer> getTimer() {
        log.info("GET /api/timer called");
        Timer timer = timerService.getTimer();
        return ResponseEntity.ok(timer);
    }

    @PostMapping
    public ResponseEntity<Timer> createTimer(@Valid @RequestBody Timer timer) {
        log.info("POST /api/timer called with name={}", timer.getName());
        Timer created = timerService.createTimer(timer);
        return ResponseEntity.ok(created);
    }

    @PutMapping
    public ResponseEntity<Timer> updateTimer(@Valid @RequestBody Timer timer) {
        log.info("PUT /api/timer called with name={}", timer.getName());
        Timer updated = timerService.updateTimer(timer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTimer() {
        log.info("DELETE /api/timer called");
        timerService.deleteTimer();
        return ResponseEntity.noContent().build();
    }
}
