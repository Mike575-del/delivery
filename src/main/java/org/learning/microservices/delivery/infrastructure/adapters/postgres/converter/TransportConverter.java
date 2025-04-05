package org.learning.microservices.delivery.infrastructure.adapters.postgres.converter;

import jakarta.persistence.AttributeConverter;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Transport;

import java.lang.reflect.Field;

public class TransportConverter implements AttributeConverter<Transport, String> {
    @Override
    public String convertToDatabaseColumn(Transport transport) {

        return transport.getName();
    }

    @Override
    public Transport convertToEntityAttribute(String name) {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field: fields){
            try {
                if (field.getType().equals(Transport.class) && ((Transport) field.get(null)).getName().equals(name)){
                    return (Transport) field.get(null);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
