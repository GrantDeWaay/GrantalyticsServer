package com.example.grantalyticsserver.service;

import com.example.grantalyticsserver.dto.GenerateLogIdResponseDto;
import com.example.grantalyticsserver.dto.LogEventDto;
import com.example.grantalyticsserver.model.LogEvent;
import com.example.grantalyticsserver.repository.LogEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
        Optional<LogEvent> logEvent = logEventRepository.findById(logEventDto.id);
        LogEvent logEventFound;
        if(logEvent.isPresent()) {
            logEventFound = logEvent.get();
        }
        else {
            return null;
        }
        logEventFound.update(logEventDto.pathVariable, logEventDto.visitDuration, logEventDto.videoPlayed, logEventDto.interactions, new Date().toInstant());
        return logEventRepository.save(logEventFound);
    }

    public GenerateLogIdResponseDto generateLog(){
        LogEvent createdLogEvent = logEventRepository.save(new LogEvent());
        return new GenerateLogIdResponseDto(createdLogEvent.getId());
    }
}
