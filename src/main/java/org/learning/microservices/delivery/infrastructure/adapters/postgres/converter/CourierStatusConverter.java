package org.learning.microservices.delivery.infrastructure.adapters.postgres.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.CourierStatus;

@Converter
public class CourierStatusConverter implements AttributeConverter<CourierStatus, String> {
    @Override
    public String convertToDatabaseColumn(CourierStatus courierStatus) {

        return courierStatus.getName();
    }

    @Override
    public CourierStatus convertToEntityAttribute(String s) {
        for (CourierStatus status: CourierStatus.values()){
            if (status.getName().equals(s)){
                return status;
            }
        }
        return null;
    }
}
