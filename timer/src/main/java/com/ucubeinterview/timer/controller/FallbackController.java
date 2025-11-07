package com.ucubeinterview.timer.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FallbackController {
    @RequestMapping("/**")
    public ResponseEntity<Object> handleUnknownPath() {
        return ResponseEntity.status(404).body(Map.of(
                "error", "Endpoint not found",
                "status", 404,
                "message", "The requested API path does not exist"
        ));
    }
}
