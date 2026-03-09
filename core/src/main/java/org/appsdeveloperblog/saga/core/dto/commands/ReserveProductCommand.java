package org.appsdeveloperblog.saga.core.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReserveProductCommand {

    UUID productId;
    UUID orderId;
    Integer productQuantity;
}
