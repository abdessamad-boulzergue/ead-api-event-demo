package com.eda.api.event.service;

import com.eda.api.event.db.config.EventEntitySearchView;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationStrength;
import com.mongodb.client.model.Facet;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.descending;

@Service
public class SearchMongoCollection {

    private final MongoCollection<EventEntity> eventEntityMongoCollection;

    public SearchMongoCollection(MongoCollection<EventEntity> eventEntityMongoCollection) {
        this.eventEntityMongoCollection = eventEntityMongoCollection;
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
}
