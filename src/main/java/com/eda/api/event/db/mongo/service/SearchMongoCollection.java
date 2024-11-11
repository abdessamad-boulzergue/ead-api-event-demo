package com.eda.api.event.db.mongo.service;

import com.eda.api.event.db.mapper.LogEventMapper;
import com.eda.api.event.db.mongo.model.EventEntity;
import com.eda.api.event.db.mongo.model.EventEntitySearchView;
import com.eda.api.event.domain.model.LogEvent;
import com.eda.api.event.domain.port.EventDataSource;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationStrength;
import com.mongodb.client.model.Facet;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.descending;

public class SearchMongoCollection implements EventDataSource {

    private final MongoCollection<EventEntity> eventEntityMongoCollection;
    private final LogEventMapper logEventMapper;

    public SearchMongoCollection(MongoCollection<EventEntity> eventEntityMongoCollection, LogEventMapper logEventMapper) {
        this.eventEntityMongoCollection = eventEntityMongoCollection;
        this.logEventMapper = logEventMapper;
    }

    public EventEntitySearchView search(){
        Bson sort = Aggregates.sort(descending("eventId"));
        Bson facet = createFacet(0, 10);
        return eventEntityMongoCollection
                .aggregate(Arrays.asList(facet,sort),EventEntitySearchView.class)
                .collation(Collation.builder()
                        .locale("fr")
                        .collationStrength(CollationStrength.PRIMARY)
                        .build())
                .first();
    }

    private Bson createFacet(int start, int limit) {
        return facet(
                new Facet("metadata",count("totalElements")),
                new Facet("data",
                        skip(start),
                        limit(limit)
                        )
        );
    }

    @Override
    public List<LogEvent> find(LocalDateTime start, LocalDateTime end) {
        var events = search();
        return logEventMapper.entitiesToLogEvents(events.getData());
    }
}
