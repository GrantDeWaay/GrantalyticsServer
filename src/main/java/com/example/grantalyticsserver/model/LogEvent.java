package com.example.grantalyticsserver.model;

import com.example.grantalyticsserver.dto.VideoTrackingDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Document("LogEvent")
public class LogEvent {

    private String pathVariable;
    private Double visitDuration;
    private VideoTracking videoTracking;
    private List<String> interactions;
    private Instant requestReceivedAt;


    public LogEvent(String pathVariable, Double visitDuration, VideoTrackingDto videoTracking, List<String> interactions, Instant requestReceivedAt) {
        super();
        this.pathVariable = pathVariable;
        this.visitDuration = visitDuration;
        this.videoTracking = new VideoTracking(videoTracking);
        this.interactions = interactions;
        this.requestReceivedAt = requestReceivedAt;
    }
}
