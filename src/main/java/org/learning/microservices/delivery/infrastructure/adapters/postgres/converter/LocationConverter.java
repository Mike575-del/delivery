package org.learning.microservices.delivery.infrastructure.adapters.postgres.converter;

import jakarta.persistence.AttributeConverter;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

public class LocationConverter implements AttributeConverter<Location, String> {
    @Override
    public String convertToDatabaseColumn(Location location) {
        return location.getX() + ", " + location.getY();
    }

    @Override
    public Location convertToEntityAttribute(String s) {
        String[] coordinates = s.split(",");
        try {
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            return Location.getFromCoordinates(x, y);
        } catch (NumberFormatException e){
            return null;
        }
    }
}
