package org.learning.microservices.delivery.core.application.use_cases.query.get.unfinished.orders;

import lombok.AllArgsConstructor;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.OrderJpaRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GetUnfinishedOrdersHandler {

    private final OrderJpaRepository orderJpaRepository;

    public GetUnfinishedOrdersResponse handle(){
        List<GetUnfinishedOrdersQueryModel> orders = new ArrayList<>();
        orderJpaRepository.findAllUnfinishedOrders().forEach(
                orderEntity -> orders.add(
                        new GetUnfinishedOrdersQueryModel(orderEntity.getId(), orderEntity.getLocation())
                )
        );
        return new GetUnfinishedOrdersResponse(orders);
    }


}
