package com.eda.api.event.db.config;

public class Metadata {
    private Integer totalElements;

    public Integer getTotalElements() {
        return totalElements;
    }

    public Metadata setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "totalElements=" + totalElements +
                '}';
    }
}
