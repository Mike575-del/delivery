package org.learning.microservices.delivery.core.domain.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.CourierStatus;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Transport;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.learning.microservices.delivery.infrastructure.exceptions.InvalidStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class DispatchServiceTest {

    private DispatchService testService = new DispatchService();

    private static final List<Location> COURIER_LOCATIONS = List.of(
            Location.getFromCoordinates(1,1),
            Location.getFromCoordinates(2,7),
            Location.getFromCoordinates(1,1),
            Location.getFromCoordinates(4,3),
            Location.getFromCoordinates(8,10),
            Location.getFromCoordinates(9,2),
            Location.getFromCoordinates(5,5),
            Location.getFromCoordinates(10,3)
    );

    @Test
    void shouldThrowInvalidStatusExceptionIfOrderIsNotCreated() {
        Order testOrder = new Order(UUID.randomUUID(), Location.getRandomLocation());
        List<Courier> couriers = getFixture();
        testOrder.assign(UUID.randomUUID());
        assertThrows(InvalidStatusException.class, () -> testService.dispatch(testOrder,couriers));
    }

    @Test
    void shouldThrowNullPointerExceptionIfAllCouriersAreBusy(){
        Order testOrder = new Order(UUID.randomUUID(), Location.getRandomLocation());
        List<Courier> couriers = getFixture();
        couriers.stream().forEach(courier -> courier.setBusy());
        assertThrows(NullPointerException.class, () -> testService.dispatch(testOrder, couriers));
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldReturnCorrectCourierNameWhoCanDeliverOrder(String expected, Location orderLocation){

        Order testOrder = new Order(UUID.randomUUID(), orderLocation);

        List<Courier> couriers = getFixture();

        Courier actualIdealCandidate = testService.dispatch(testOrder, couriers);

        String actual = actualIdealCandidate.getName();

        assertEquals(expected, actual);
        assertEquals(actualIdealCandidate.getId(),testOrder.getCourierId());
        assertEquals(CourierStatus.Busy, actualIdealCandidate.getStatus());

    }


    private static Stream<Arguments> provideParameters(){
        return Stream.of(
                Arguments.of("courier 4", Location.getFromCoordinates(2,3)),
                Arguments.of("courier 2", Location.getFromCoordinates(4,7)),
                Arguments.of("courier 1", Location.START_LOCATION),
                Arguments.of("courier 5", Location.getFromCoordinates(8,8)),
                Arguments.of("courier 2", Location.getFromCoordinates(1,10))
        );
    }

    private static List<Courier> getFixture(){
        List<Courier> couriers = new ArrayList<>();
        int i = 0;

        for (Location location: COURIER_LOCATIONS){
            couriers.add(
                    new Courier(UUID.randomUUID(),
                            "courier " + ++i,
                            "transport " + i,
                            (i % Transport.MAX_SPEED) + 1,
                            location)
            );
        }
        return couriers;
    }


}