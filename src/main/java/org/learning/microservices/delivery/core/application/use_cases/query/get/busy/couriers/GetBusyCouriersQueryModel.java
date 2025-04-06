package org.learning.microservices.delivery.core.application.use_cases.query.get.busy.couriers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetBusyCouriersQueryModel {
    private UUID id;
    private String name;
    private Location location;
}
