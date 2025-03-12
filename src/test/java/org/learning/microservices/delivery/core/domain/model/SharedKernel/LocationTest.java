package org.learning.microservices.delivery.core.domain.model.SharedKernel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @ParameterizedTest
    @CsvSource({"-1,5", "4, 0", "12,3", "9, 15", "0, 12", "-1231, 53478"})
    void shouldThrowIllegalArgumentExceptionForInvalidCoordinates(int x, int y) {
       assertThrows(IllegalArgumentException.class, () -> Location.getFromCoordinates(x, y));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, true, true, true, true})
    void shouldReturnRandomLocation(boolean needToGenerate) {
        assertDoesNotThrow(() -> Location.getRandomLocation());

    }

    @Test
    void shouldReturnTrueWhenTwoLocationsAreEquals() {
        Location testLocationOne = Location.getFromCoordinates(5,5);
        Location testLocationTwo= Location.getFromCoordinates(5,5);
        assertTrue(testLocationOne.equals(testLocationTwo));
    }

    @Test
    void shouldReturnFalseWhenTwoLocationsAreDifferent() {
        Location testLocationOne = Location.getFromCoordinates(5,5);
        Location testLocationTwo= Location.getFromCoordinates(1,5);
        assertFalse(testLocationOne.equals(testLocationTwo));
    }

    @ParameterizedTest
    @CsvSource({"1,7,4,5,5","10,2,10,1,1", "4,8,9,1,12", "1,7,4,5,5", "2,1,10,10,17"})
    void shouldReturnCorrectDistance(int firstX, int firstY, int secondX, int secondY, int expected) {
        Location firstLocation = Location.getFromCoordinates(firstX, firstY);
        Location secondLocation = Location.getFromCoordinates(secondX, secondY);
        assertEquals(expected, firstLocation.getDistance(secondLocation));
    }
}