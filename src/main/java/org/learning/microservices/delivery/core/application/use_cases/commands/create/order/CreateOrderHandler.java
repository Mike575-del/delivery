package org.learning.microservices.delivery.core.application.use_cases.commands.create.order;

import lombok.AllArgsConstructor;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.learning.microservices.delivery.core.ports.IOrderRepository;

@AllArgsConstructor
public class CreateOrderHandler {
    private final IOrderRepository orderRepository;

    public void handle(CreateOrderCommand command){
        Order newOrder = new Order(command.getBasketId(), Location.getRandomLocation());
        orderRepository.addOrder(newOrder);
    }

}
