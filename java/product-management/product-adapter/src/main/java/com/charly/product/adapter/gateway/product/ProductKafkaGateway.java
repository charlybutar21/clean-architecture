package com.charly.product.adapter.gateway.product;

import com.charly.product.adapter.messaging.producer.KafkaEventProducer;
import com.charly.product.application.gateway.product.ProductEventGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductKafkaGateway implements ProductEventGateway {

    private final KafkaEventProducer kafkaEventProducer;

    @Value("${kafka.topic.product}")
    private String productTopic;

    @Override
    public void publishProductCreatedEvent(String productEventJson) {
        kafkaEventProducer.publishEvent(productTopic, productEventJson);
    }
}
