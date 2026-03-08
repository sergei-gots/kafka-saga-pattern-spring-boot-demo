package org.appsdeveloperblog.saga.orders.service;

import org.appsdeveloperblog.saga.core.dto.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
