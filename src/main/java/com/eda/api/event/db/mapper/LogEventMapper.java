package com.eda.api.event.db.mapper;

import com.eda.api.event.db.mongo.model.EventEntity;
import com.eda.api.event.domain.model.LogEvent;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Message mapper using Spring component model
 */
@Mapper(componentModel = "spring")
@Component
public interface LogEventMapper {

    LogEvent entityToLogEvent(EventEntity entity);
    List<LogEvent> entitiesToLogEvents(List<EventEntity> entity);
}
