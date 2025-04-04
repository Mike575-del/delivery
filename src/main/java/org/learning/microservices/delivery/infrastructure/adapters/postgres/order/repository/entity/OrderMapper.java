package org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.entity;

import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order toModel(OrderEntity entity);

    OrderEntity toEntity(Order model);
}
