package org.appsdeveloperblog.saga.products.service.handler;

import org.appsdeveloperblog.saga.core.dto.commands.ReserveProductCommand;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${product.commands.topic.name}")
public class ReserveProductCommandHandler {

    @KafkaHandler
    public void handleReserveProductCommand(ReserveProductCommand reserveProductCommand) {
        // ToDo
        System.out.println("ReserveProductCommand received");
    }
}
