package org.learning.microservices.delivery.core.domain.model.courieraggregate;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldReturnCorrectTimeToTarget(Location from, Location to, int expected){
        Courier testCourier = new Courier(UUID.randomUUID(), "test-courier",
                "bike", 3, from);
        int actual = testCourier.calculateTimeToLocation(to);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideParameters(){
        return Stream.of(
                Arguments.of(Location.getFromCoordinates(2,5),Location.getFromCoordinates(1,9),2),
                Arguments.of(Location.getFromCoordinates(4,7),Location.getFromCoordinates(1,1),3),
                Arguments.of(Location.getFromCoordinates(1,1),Location.getFromCoordinates(1,2),1),
                Arguments.of(Location.getFromCoordinates(5,10),Location.getFromCoordinates(7,8),2)
        );
    }

}