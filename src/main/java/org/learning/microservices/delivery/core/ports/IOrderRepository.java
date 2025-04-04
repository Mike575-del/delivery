package org.learning.microservices.delivery.core.ports;

import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderRepository {

    void addOrder(Order order);

    void updateOrder(Order order);

    Order getOrderById(UUID id);

    Order getAnyCreatedOrder();

    List<Order> getAllAssignedOrders();
}
