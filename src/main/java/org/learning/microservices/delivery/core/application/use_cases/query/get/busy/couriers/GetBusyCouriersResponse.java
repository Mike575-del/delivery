package org.learning.microservices.delivery.core.application.use_cases.query.get.busy.couriers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetBusyCouriersResponse {

    private final List<GetBusyCouriersQueryModel> couriers;

}
