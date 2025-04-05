package org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository;

import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.OrderStatus;
import org.learning.microservices.delivery.core.ports.IOrderRepository;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.entity.OrderEntity;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.entity.OrderMapper;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.repository.OrderJpaRepository;

import java.util.List;
import java.util.UUID;

public class OrderRepository implements IOrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper mapper;

    public OrderRepository(OrderJpaRepository orderJpaRepository, OrderMapper mapper){
        this.orderJpaRepository = orderJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void addOrder(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        orderJpaRepository.save(entity);
    }

    @Override
    public void updateOrder(Order order) {
        orderJpaRepository.deleteById(order.getId());
        OrderEntity entity = mapper.toEntity(order);
        orderJpaRepository.save(entity);
    }

    @Override
    public Order getOrderById(UUID id) {

        return mapper.toModel(orderJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public Order getAnyCreatedOrder() {

        return orderJpaRepository.findAll().stream()
                .map(mapper::toModel)
                .filter(order -> order.getStatus().equals(OrderStatus.Created))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<Order> getAllAssignedOrders() {
        return orderJpaRepository.findAll().stream()
                .map(mapper::toModel)
                .filter(order -> order.getStatus().equals(OrderStatus.Assigned))
                .toList();
    }
}
