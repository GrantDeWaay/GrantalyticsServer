package com.example.grantalyticsserver.controller;

import com.example.grantalyticsserver.dto.LogEventDto;
import com.example.grantalyticsserver.model.LogEvent;
import com.example.grantalyticsserver.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GrantalyticsController {

    private final LoggingService loggingService;
    @Autowired
    public GrantalyticsController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    // Existing endpoint to create a product and update materials
    @PostMapping("/log-visit")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> logVisit(@RequestBody LogEventDto logEventDto) {
        LogEvent logEvent = loggingService.logEvent(logEventDto);
        if(logEvent == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Visit Successfully Logged.", HttpStatus.OK);
    }

}
