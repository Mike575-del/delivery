package org.learning.microservices.delivery.core.domain.services;

import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.CourierStatus;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.OrderStatus;
import org.learning.microservices.delivery.infrastructure.exceptions.InvalidStatusException;


import java.util.List;

public class DispatchService implements IDispatchService{
    @Override
    public Courier dispatch(Order order, List<Courier> couriers) {
        if (!order.getStatus().equals(OrderStatus.Created)){
            throw new InvalidStatusException("The order must have the status \"Created\"");
        }
        Location target = order.getLocation();
        List<Courier> freeCouriers = getFreeCouriers(couriers);

        int minimumTimeToTarget = freeCouriers.stream().
                mapToInt(c -> c.calculateTimeToLocation(target)).min().getAsInt();
        return freeCouriers.stream()
                .filter(courier -> courier.calculateTimeToLocation(target) == minimumTimeToTarget)
                .findFirst().get();
    }

    private List<Courier> getFreeCouriers(List<Courier> allCouriers){
        List<Courier> freeCouriers = allCouriers.stream()
                .filter(courier -> courier.getStatus().equals(CourierStatus.Free))
                .toList();
        if (freeCouriers.isEmpty()){
            throw new NullPointerException("There are no free couriers");
        }
        return freeCouriers;
    }
}
