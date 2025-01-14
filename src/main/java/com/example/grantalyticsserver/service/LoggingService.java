package com.example.grantalyticsserver.service;

import com.example.grantalyticsserver.dto.LogEventDto;
import com.example.grantalyticsserver.model.LogEvent;
import com.example.grantalyticsserver.repository.LogEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

@Service
public class LoggingService {

    private final LogEventRepository logEventRepository;

    @Autowired
    public LoggingService( LogEventRepository logEventRepository) {
        this.logEventRepository = logEventRepository;
    }

    public LogEvent logEvent(LogEventDto logEventDto) {

        // if the duration of the visit is greater than 3 hours, then ignore it
        if(logEventDto.visitDuration > 10800) {
            return null;
        }
        return logEventRepository.save(new LogEvent(logEventDto.pathVariable, logEventDto.visitDuration, logEventDto.videoTracking, logEventDto.interactions, new Date().toInstant()));
    }
}
