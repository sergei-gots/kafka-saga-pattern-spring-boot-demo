package org.appsdeveloperblog.saga.core.dto.commands;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveProductCommand {

    UUID productId;
    UUID orderId;
    Integer productQuantity;
}
