package com.eda.api.event.config;

import com.eda.api.event.db.mapper.LogEventMapper;
import com.eda.api.event.db.mongo.model.EventEntity;
import com.eda.api.event.db.mongo.service.SearchMongoCollection;
import com.eda.api.event.domain.port.EventDataSource;
import com.eda.api.event.domain.service.EventFinder;
import com.eda.api.event.domain.usecase.SearchEvent;
import com.mongodb.client.MongoCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.eda.api.event.db.mapper")
public class ApplicationBeansConfig {

    @Bean
    EventDataSource eventDataSource(MongoCollection<EventEntity> mongodb, LogEventMapper mapper){
        return new SearchMongoCollection(mongodb, mapper);
    }

    @Bean
    SearchEvent searchEvent(EventDataSource eventDataSource){
        return new EventFinder(eventDataSource);
    }
}
