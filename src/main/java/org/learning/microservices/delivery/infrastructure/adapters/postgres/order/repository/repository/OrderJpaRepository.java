package org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.repository;


import org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
