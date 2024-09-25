package com.order.process.infrastructure.config;

import com.order.process.domain.repository.IInventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jms.JmsMessageDrivenEndpoint;
import jakarta.jms.ConnectionFactory;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class StockIntegrationConfig {

    private static final String CHANNEL_NAME = "stockChannel";

    private final IInventoryRepository inventoryRepository;

    public StockIntegrationConfig(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Bean
    public DirectChannel stockChannel() {
        return new DirectChannel();
    }

    @Bean
    public JmsMessageDrivenEndpoint stockListener(ConnectionFactory connectionFactory) {
        return Jms.messageDrivenChannelAdapter(connectionFactory)
                .destination(CHANNEL_NAME)
                .outputChannel(stockChannel())
                .getObject();
    }

    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME)
    public MessageHandler stockHandler() {
        return  message -> {
            String productId = (String) message.getHeaders().get("productId");
            int quantity = (int) message.getPayload();
            inventoryRepository.newQuantity(productId, quantity);
        };
    }

}
