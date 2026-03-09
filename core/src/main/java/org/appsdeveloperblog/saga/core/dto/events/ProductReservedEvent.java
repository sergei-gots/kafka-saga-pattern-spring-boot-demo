package org.appsdeveloperblog.saga.core.dto.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReservedEvent {

    private UUID orderId;
    private UUID productId;
    BigDecimal productPrice;
    Integer productQuantity;
}
