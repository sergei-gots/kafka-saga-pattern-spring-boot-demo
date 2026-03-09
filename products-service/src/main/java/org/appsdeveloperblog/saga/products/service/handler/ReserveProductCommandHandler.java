package org.appsdeveloperblog.saga.products.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.appsdeveloperblog.saga.core.dto.Product;
import org.appsdeveloperblog.saga.core.dto.commands.ReserveProductCommand;
import org.appsdeveloperblog.saga.core.exceptions.ProductInsufficientQuantityException;
import org.appsdeveloperblog.saga.products.service.ProductService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "${product.commands.topic.name}")
public class ReserveProductCommandHandler {

    private final ProductService productsService;

    public ReserveProductCommandHandler(ProductService productsService) {
        this.productsService = productsService;
    }

    @KafkaHandler
    public void handleReserveProductCommand(@Payload ReserveProductCommand reserveProductCommand) {

        Product product = new Product(
                reserveProductCommand.getProductId(),
                reserveProductCommand.getProductQuantity()
        );

        try {
            productsService.reserve(product, reserveProductCommand.getOrderId());
        }
        catch (ProductInsufficientQuantityException e) {
            log.error(e.getLocalizedMessage());
        }

    }
}
