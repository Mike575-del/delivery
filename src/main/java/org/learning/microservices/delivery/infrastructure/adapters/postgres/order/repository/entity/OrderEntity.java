package org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.OrderStatus;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.converter.OrderStatusConverter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private  UUID id;

    @Column
    private Location location;

    @Column
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    @Column(name = "courier_id")
    private UUID courierId;
}
