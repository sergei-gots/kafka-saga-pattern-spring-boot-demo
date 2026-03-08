package org.appsdeveloperblog.saga.core.dto.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreatedEvent {

    private UUID orderId;
    private UUID customerId;
    private UUID productId;

    Integer productQuantity;
}
