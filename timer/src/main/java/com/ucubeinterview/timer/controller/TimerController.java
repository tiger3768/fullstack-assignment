package com.ucubeinterview.timer.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.ucubeinterview.timer.model.Timer;
import com.ucubeinterview.timer.service.TimerService;

@RestController
@RequestMapping("/api/timer")
@CrossOrigin(origins = "*")
public class TimerController {
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping
    public Timer getTimer() {
        return timerService.getTimer();
    }

    @PostMapping
    public Timer createTimer(@Valid @RequestBody Timer timer) {
        return timerService.createTimer(timer);
    }

    @PutMapping
    public Timer updateTimer(@Valid @RequestBody Timer timer) {
        return timerService.updateTimer(timer);
    }

    @DeleteMapping
    public void deleteTimer() {
        timerService.deleteTimer();
    }
}
