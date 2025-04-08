package org.learning.microservices.delivery.core.ports;

import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;

import java.util.List;
import java.util.UUID;

public interface ICourierRepository {

    void addCourier(Courier courier);

    void updateCourier(Courier courier);

    Courier getCourierById(UUID id);

    List<Courier> getAllFreeCouriers();

    List<Courier> getAllBusyCouriers();

}
