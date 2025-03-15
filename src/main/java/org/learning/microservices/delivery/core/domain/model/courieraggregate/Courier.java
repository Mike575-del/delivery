package org.learning.microservices.delivery.core.domain.model.courieraggregate;

import static org.learning.microservices.delivery.core.domain.model.courieraggregate.CourierStatus.*;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

import java.util.UUID;

public class Courier {
    private final UUID id;
    private String name;
    private Transport transport;
    private Location location;
    private CourierStatus status;

    public Courier(UUID id, String name, String transportName, int transportSpeed, Location location){
        this.id = id;
        this.name = name;
        this.transport = new Transport(transportName, transportSpeed);
        this.location = location;
        this.status = Free;
    }

    public void setFree(){
        this.status = Free;
    }

    public void setBusy(){
        this.status = Busy;
    }

    public void move(Location target){
        this.location = this.transport.move(this.location, target);
    }

    public int calculateTimeToLocation(Location target){
        int distance = this.location.getDistance(target);
        return (int) Math.ceil((double) distance / transport.getSpeed());
    }
}
