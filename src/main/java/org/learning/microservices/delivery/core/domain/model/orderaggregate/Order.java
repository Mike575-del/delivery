package org.learning.microservices.delivery.core.domain.model.orderaggregate;

import static org.learning.microservices.delivery.core.domain.model.orderaggregate.OrderStatus.*;

import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.infrastructure.exceptions.InvalidStatusException;
import java.util.UUID;

public class Order {

    private final UUID id;
    private Location location;
    private OrderStatus status;
    private UUID courierId;

    public Order(UUID id, Location location){
        this.id = id;
        this.location = location;
        this.status = Created;
    }

    public void assign(UUID courierId){
        if (!status.equals(Created)){
            throw new InvalidStatusException("Cannot assign order. Check order status!");
        }
        this.courierId = courierId;
        this.status = OrderStatus.Assigned;
    }

    public void complete(){
        if (!status.equals(Assigned)){
            throw new InvalidStatusException("Cannot complete order. Check order status!");
        }
        this.status = Completed;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Order){
            return this.id.equals(((Order) other).id);
        }
        return false;
    }

    public UUID getCourierId(){
        return courierId;
    }

    public Location getLocation() {
        return location;
    }

    public OrderStatus getStatus() {
        return status;
    }


}
