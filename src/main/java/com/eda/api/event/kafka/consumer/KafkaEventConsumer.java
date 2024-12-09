package com.eda.api.event.kafka.consumer;

import com.eda.api.event.avro.LogEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        prefix = "orti.eda",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class KafkaEventConsumer {
    @KafkaListener(topics = "topic-name-v1",groupId = "consumer-group-id", id = "consumer-id")
    public void consume(LogEvent message){
        System.out.printf("MSG RECEIVED : %s \n",message);
    }
}
