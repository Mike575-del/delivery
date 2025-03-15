package org.learning.microservices.delivery.core.domain.model.courieraggregate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {

    @Test
    void shouldReturnFalseWhenTwoTransportsAreNotEquals() {
        Transport transportFirst = new Transport("test-name",1);
        Transport transportSecond = new Transport("test-name", 1);
        assertFalse(transportFirst.equals(transportSecond));
    }

    @Test
    void shouldThrowNullPointerExceptionWhenNameIsEmpty(){
        assertThrows(NullPointerException.class, () -> new Transport(null,1));
        assertThrows(NullPointerException.class, () -> new Transport("",1));
        assertThrows(NullPointerException.class, () -> new Transport(" ",1));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSpeedIsOutOfRange(){
        assertThrows(IllegalArgumentException.class, () -> new Transport("tst-name", 0));
        assertThrows(IllegalArgumentException.class, () -> new Transport("tst-name", 4));
        assertThrows(IllegalArgumentException.class, () -> new Transport("tst-name", -1));
    }

    @Test
    void shouldReturnTrueWhenTwoTransportsAreEquals(){
        UUID uuid = UUID.randomUUID();
        Transport transportFirst = new Transport(uuid, "test-name", 1);
        Transport transportSecond = new Transport(uuid, "test-name", 1);
        assertTrue(transportFirst.equals(transportSecond));
    }


    @ParameterizedTest
    @CsvSource({"3,7,3,7,3,7",
            "1,1,1,9,1,4",
            "4,6,1,5,1,6",
            "4,5,4,10,4,8",
            "1,9,1,5,1,6",
            "3,3,3,4,3,4",
            "8,4,7,3,7,3",
            "4,6,2,4,2,5"
    })
    void shouldReturnCorrectLocation(int fromX, int fromY, int toX, int toY, int expectedX, int expectedY) {
        Transport transport = new Transport("name", 3);
        Location from = Location.getFromCoordinates(fromX, fromY);
        Location to = Location.getFromCoordinates(toX, toY);
        Location actual = transport.move(from, to);

        Location expected = Location.getFromCoordinates(expectedX, expectedY);

        assertTrue(expected.equals(actual));
    }
}