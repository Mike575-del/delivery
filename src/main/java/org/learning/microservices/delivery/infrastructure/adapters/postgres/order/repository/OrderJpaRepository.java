package org.learning.microservices.delivery.infrastructure.adapters.postgres.order.repository;


import org.learning.microservices.delivery.infrastructure.adapters.postgres.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("select o from orders o where o.status!=\"Completed\"")
    List<OrderEntity> findAllUnfinishedOrders();

}
