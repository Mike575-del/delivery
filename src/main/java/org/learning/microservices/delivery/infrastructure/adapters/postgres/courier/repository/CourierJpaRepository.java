package org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.repository;


import org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierJpaRepository extends JpaRepository<CourierEntity, UUID> {
}
