package org.appsdeveloperblog.saga.orders.service;

import org.appsdeveloperblog.saga.core.dto.Order;
import org.appsdeveloperblog.saga.core.types.OrderStatus;
import org.appsdeveloperblog.saga.orders.dao.jpa.entity.OrderEntity;
import org.appsdeveloperblog.saga.orders.dao.jpa.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }

}
