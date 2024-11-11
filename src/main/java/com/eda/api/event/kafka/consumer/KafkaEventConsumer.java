package com.eda.api.event.kafka.consumer;

import com.eda.api.event.domain.model.LogEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventConsumer {
    @KafkaListener(topics = "topic-name-v1",groupId = "consumer-group-id", id = "consumer-id")
    public void consume(LogEvent message){
        System.out.printf("MSG RECEIVED : %s \n",message);
    }
}
