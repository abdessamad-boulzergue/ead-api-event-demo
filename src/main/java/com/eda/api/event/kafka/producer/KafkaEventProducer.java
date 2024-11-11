package com.eda.api.event.kafka.producer;

import com.eda.api.event.domain.model.LogEvent;
import com.eda.kafka.builder.EdaClientBuilderFactory;
import com.eda.kafka.producer.EventProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaEventProducer {

    String topic ="topic-name-v1";

    @Bean
    public EventProducer<String, LogEvent> producer(EdaClientBuilderFactory clientBuilderFactory) {
        return clientBuilderFactory.producer(topic)
                .build(LogEvent.class);
    }
}
