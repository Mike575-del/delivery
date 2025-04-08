package org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.repository;


import org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CourierJpaRepository extends JpaRepository<CourierEntity, UUID> {

    @Query("select c from couriers c where c.status=\"Busy\"")
    List<CourierEntity> findAllBusyCouriers();
}
