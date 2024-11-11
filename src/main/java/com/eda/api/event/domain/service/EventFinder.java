package com.eda.api.event.domain.service;

import com.eda.api.event.domain.model.LogEvent;
import com.eda.api.event.domain.port.EventDataSource;
import com.eda.api.event.domain.usecase.SearchEvent;

import java.time.LocalDateTime;
import java.util.List;

public class EventFinder implements SearchEvent {

    private final EventDataSource eventDataSource;

    public EventFinder(EventDataSource eventDataSource) {
        this.eventDataSource = eventDataSource;
    }

    @Override
    public List<LogEvent> search(LocalDateTime startDate, LocalDateTime endDate) {
        return eventDataSource.find(startDate,endDate);
    }
}
