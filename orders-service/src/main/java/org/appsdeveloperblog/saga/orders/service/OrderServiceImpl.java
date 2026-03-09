package org.appsdeveloperblog.saga.orders.service;

import org.appsdeveloperblog.saga.core.dto.Order;
import org.appsdeveloperblog.saga.core.dto.events.OrderCreatedEvent;
import org.appsdeveloperblog.saga.core.types.OrderStatus;
import org.appsdeveloperblog.saga.orders.dao.jpa.entity.OrderEntity;
import org.appsdeveloperblog.saga.orders.dao.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String orderEventsTopicName;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${order.events.topic.name}") String orderEventsTopicName) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.orderEventsTopicName = orderEventsTopicName;
    }

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        OrderEntity savedEntity = orderRepository.save(entity);

        OrderCreatedEvent placedOrderEvent = new OrderCreatedEvent();
        placedOrderEvent.setOrderId(savedEntity.getId());
        placedOrderEvent.setProductId(savedEntity.getProductId());
        placedOrderEvent.setCustomerId(savedEntity.getCustomerId());
        placedOrderEvent.setProductQuantity(savedEntity.getProductQuantity());

        kafkaTemplate.send(orderEventsTopicName, placedOrderEvent);

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }

}
