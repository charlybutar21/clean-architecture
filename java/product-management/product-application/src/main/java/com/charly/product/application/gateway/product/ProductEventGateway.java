package com.charly.product.application.gateway.product;

public interface ProductEventGateway {
    void publishProductCreatedEvent(String productEventJson);
}
