package org.appsdeveloperblog.saga.products.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.appsdeveloperblog.saga.core.dto.Product;
import org.appsdeveloperblog.saga.core.dto.commands.ReserveProductCommand;
import org.appsdeveloperblog.saga.core.dto.events.ProductReservedEvent;
import org.appsdeveloperblog.saga.core.exceptions.ProductInsufficientQuantityException;
import org.appsdeveloperblog.saga.products.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@KafkaListener(topics = "${product.commands.topic.name}")
public class ReserveProductCommandHandler {

    private final ProductService productsService;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String productReservedEventsTopicName;

    public ReserveProductCommandHandler(
            ProductService productsService,
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${product.reserved.events.topic.name}") String productReservedEventsTopicName
    ) {
        this.productsService = productsService;
        this.kafkaTemplate = kafkaTemplate;
        this.productReservedEventsTopicName = productReservedEventsTopicName;
    }

    @KafkaHandler
    public void handleReserveProductCommand(@Payload ReserveProductCommand reserveProductCommand) {

        Product product = new Product(
                reserveProductCommand.getProductId(),
                reserveProductCommand.getProductQuantity()
        );

        try {
            UUID orderId = reserveProductCommand.getOrderId();
            productsService.reserve(product, orderId);
            sendProductReservedEvent(product, orderId);
        }
        catch (ProductInsufficientQuantityException e) {
            log.error(e.getLocalizedMessage());
        }

    }

    private void sendProductReservedEvent(Product product, UUID orderId) {

        ProductReservedEvent productReservedEvent = new ProductReservedEvent(
                orderId,
                product.getId(),
                product.getPrice(),
                product.getQuantity()
        );
        kafkaTemplate.send(productReservedEventsTopicName, productReservedEvent);
    }
}
