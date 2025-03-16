package com.charly.product.adapter.messaging.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishEvent(String topic, String eventJson) {
        kafkaTemplate.send(topic, eventJson);
    }
}
