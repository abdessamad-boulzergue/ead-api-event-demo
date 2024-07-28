package com.eda.api.event.db.config;

import com.eda.api.event.service.EventEntity;

import java.util.List;

public class EventEntitySearchView {

    private List<EventEntity> data;
    private List<Metadata> metadata;

    public EventEntitySearchView() {

    }
    public EventEntitySearchView(List<EventEntity> data, List<Metadata> metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public List<EventEntity> getData() {
        return data;
    }

    public void setData(List<EventEntity> data) {
        this.data = data;
    }

    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "EventEntitySearchView{" +
                "data=" + data +
                ", metadata=" + metadata +
                '}';
    }
}