package org.appsdeveloperblog.saga.orders.saga;

import lombok.extern.slf4j.Slf4j;
import org.appsdeveloperblog.saga.core.dto.commands.ReserveProductCommand;
import org.appsdeveloperblog.saga.core.dto.events.OrderCreatedEvent;
import org.appsdeveloperblog.saga.core.types.OrderStatus;
import org.appsdeveloperblog.saga.orders.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "${order.events.topic.name}")
public class OrderSaga {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String productCommandsTopicName;

    private final OrderHistoryService orderHistoryService;

    public OrderSaga(
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${product.commands.topic.name}") String productCommandsTopicName, OrderHistoryService orderHistoryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.productCommandsTopicName = productCommandsTopicName;
        this.orderHistoryService = orderHistoryService;
    }

    @KafkaHandler(isDefault = true)
    public void unknownEvent(@Payload Object unknown) {
        log.warn("Unknown payload received from order-events topic: {}", unknown.getClass().getName());
    }

    @KafkaHandler
    public void handleOrderCreatedEvent(@Payload OrderCreatedEvent orderCreatedEvent) {

        log.info("Received OrderCreatedEvent={}", orderCreatedEvent);

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .productQuantity(orderCreatedEvent.getProductQuantity())
        .build();

        kafkaTemplate.send(productCommandsTopicName, reserveProductCommand);
        orderHistoryService.add(orderCreatedEvent.getOrderId(), OrderStatus.CREATED);
    }
}
