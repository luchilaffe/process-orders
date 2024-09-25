package com.order.process.infrastructure.integration;

import org.springframework.messaging.MessageHeaders;

public interface Message<T> {
    T getPayload();
    MessageHeaders getHeaders();
}
