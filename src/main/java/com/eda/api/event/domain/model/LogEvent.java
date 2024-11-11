package com.eda.api.event.domain.model;

public class LogEvent {
    private String eventId;
    private String eventType;


    public String getEventId() {
        return eventId;
    }

    public LogEvent setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public LogEvent setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
