package com.eda.api.event.domain.port;

import com.eda.api.event.domain.model.LogEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface EventDataSource {

    List<LogEvent> find(LocalDateTime start, LocalDateTime end);
}
