package org.appsdeveloperblog.saga.orders.service;

import org.appsdeveloperblog.saga.core.types.OrderStatus;
import org.appsdeveloperblog.saga.orders.dto.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistory> findByOrderId(UUID orderId);
}
