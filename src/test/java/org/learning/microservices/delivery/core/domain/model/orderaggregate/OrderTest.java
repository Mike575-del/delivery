package org.learning.microservices.delivery.core.domain.model.orderaggregate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.infrastructure.exceptions.InvalidStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private static Order testOrder;

    @BeforeEach
    void createRandomOrder(){
        testOrder = new Order(UUID.randomUUID(), Location.getRandomLocation());
    }

    @Test
    void shouldOkWhenAssignCreatedOrder() {
        UUID courierId = UUID.randomUUID();
        testOrder.assign(courierId);
        assertEquals(courierId, testOrder.getCourierId());
    }

    @Test
    void shouldThrowInvalidStatusExceptionWhenTryToAssignNotCreatedOrder(){
        testOrder.assign(UUID.randomUUID());
        assertThrows(InvalidStatusException.class, ()-> testOrder.assign(UUID.randomUUID()));
    }

    @Test
    void shouldOkWhenCompleteAssignedOrder() {
        testOrder.assign(UUID.randomUUID());
        testOrder.complete();
        assertEquals(OrderStatus.Completed, testOrder.getStatus());
    }

    @Test
    void shouldThrowInvalidStatusExceptionWhenTryToCompleteUnassignedOrder() {
        assertThrows(InvalidStatusException.class, ()-> testOrder.complete());
    }
}