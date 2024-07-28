package com.eda.api.event.service;

public class EventEntity {

    private String eventId;
    private String type;
    private String details;
    private String correlationId;

    public String getEventId() {
        return eventId;
    }

    public EventEntity setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getType() {
        return type;
    }

    public EventEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public EventEntity setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public EventEntity setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "eventId='" + eventId + '\'' +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
