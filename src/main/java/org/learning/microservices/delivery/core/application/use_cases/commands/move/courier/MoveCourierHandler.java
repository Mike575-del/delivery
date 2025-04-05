package org.learning.microservices.delivery.core.application.use_cases.commands.move.courier;

import lombok.AllArgsConstructor;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.learning.microservices.delivery.core.ports.ICourierRepository;
import org.learning.microservices.delivery.core.ports.IOrderRepository;

@AllArgsConstructor
public class MoveCourierHandler {
    private final ICourierRepository courierRepository;
    private final IOrderRepository orderRepository;

    public void handle(MoveCouriersCommand command){
        for (Order order: orderRepository.getAllAssignedOrders()){
            Courier courier = courierRepository.getCourierById(order.getCourierId());
            courier.move(order.getLocation());
            courierRepository.updateCourier(courier);
        }
    }
}
