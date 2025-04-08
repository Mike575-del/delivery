package org.learning.microservices.delivery.core.application.use_cases.query.get.busy.couriers;

import lombok.AllArgsConstructor;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.repository.CourierJpaRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GetBusyCouriersHandler {
    private final CourierJpaRepository courierRepository;

    public GetBusyCouriersResponse handle(){
        List<GetBusyCouriersQueryModel> couriers = new ArrayList<>();
        courierRepository.findAllBusyCouriers().stream().forEach(
                courierEntity -> couriers.add(new GetBusyCouriersQueryModel(
                        courierEntity.getId(), courierEntity.getName(), courierEntity.getCurrentLocation()
                ))
        );
        return new GetBusyCouriersResponse(couriers);
    }
}
