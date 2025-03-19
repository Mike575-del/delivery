package org.learning.microservices.delivery.core.domain.services;

import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;

import java.util.List;

public interface IDispatchService {
    public Courier dispatch(Order order, List<Courier> couriers);
}
