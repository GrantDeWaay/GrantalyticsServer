package com.example.grantalyticsserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("LogEvent")
public class LogEvent {
    @Id
    private String id;

    private String pathVariable;
    private Double visitDuration;
    private Boolean videoPlayed;
    private List<String> interactions;
    private Instant requestReceivedAt;


    public String getPathVariable() {
        return pathVariable;
    }

    public void setPathVariable(String pathVariable) {
        this.pathVariable = pathVariable;
    }

    public Double getVisitDuration() {
        return visitDuration;
    }

    public void setVisitDuration(Double visitDuration) {
        this.visitDuration = visitDuration;
    }

    public List<String> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<String> interactions) {
        this.interactions = interactions;
    }

    public Instant getRequestReceivedAt() {
        return requestReceivedAt;
    }

    public void setRequestReceivedAt(Instant requestReceivedAt) {
        this.requestReceivedAt = requestReceivedAt;
    }

    public Boolean getVideoPlayed() {
        return videoPlayed;
    }

    public void setVideoPlayed(Boolean videoPlayed) {
        this.videoPlayed = videoPlayed;
    }

    public String getId() {
        return id;
    }


    public void update(String pathVariable, Double visitDuration, Boolean videoPlayed, List<String> interactions, Instant requestReceivedAt){
        this.pathVariable = pathVariable;
        this.visitDuration = visitDuration;
        this.videoPlayed = videoPlayed;
        this.interactions = interactions;
        this.requestReceivedAt = requestReceivedAt;
    }

    public LogEvent(){}
}
