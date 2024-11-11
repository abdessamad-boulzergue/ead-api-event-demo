package com.eda.api.event.domain.usecase;

import com.eda.api.event.domain.model.LogEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchEvent {

    List<LogEvent> search(LocalDateTime startDate, LocalDateTime endDate);
}
