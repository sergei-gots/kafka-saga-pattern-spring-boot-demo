package org.appsdeveloperblog.saga.orders.saga;

import org.appsdeveloperblog.saga.core.dto.commands.ReserveProductCommand;
import org.appsdeveloperblog.saga.core.dto.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${order.events.topic.name}")
public class OrderSaga {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String productCommandsTopicName;

    public OrderSaga(
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${product.commands.topic.name}") String productCommandsTopicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.productCommandsTopicName = productCommandsTopicName;
    }

    @KafkaHandler
    public void handleOrderCreatedEvent(@Payload OrderCreatedEvent orderCreatedEvent) {

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getCustomerId())
                .productId(orderCreatedEvent.getProductId())
                .productQuantity(orderCreatedEvent.getProductQuantity())
        .build();

        kafkaTemplate.send(productCommandsTopicName, reserveProductCommand);

    }
}
