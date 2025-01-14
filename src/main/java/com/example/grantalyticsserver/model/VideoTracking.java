package com.example.grantalyticsserver.model;

import com.example.grantalyticsserver.dto.VideoTrackingDto;

public class VideoTracking {
    public boolean played;
    public Double videoMaxTime;

    public VideoTracking(boolean played, Double videoMaxTime) {
        this.played = played;
        this.videoMaxTime = videoMaxTime;
    }

    public VideoTracking(VideoTrackingDto videoTrackingDto) {
        this.played = videoTrackingDto.played;
        this.videoMaxTime = videoTrackingDto.videoMaxTime;
    }
}
