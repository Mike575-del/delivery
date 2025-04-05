package org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.entity;

import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.mapstruct.Mapper;

@Mapper
public interface CourierMapper {

    Courier toModel(CourierEntity entity);

    CourierEntity toEntity(Courier model);
}
