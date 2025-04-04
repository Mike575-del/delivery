package org.learning.microservices.delivery.infrastructure.adapters.postgres.converter;

import jakarta.persistence.AttributeConverter;
import org.apache.commons.text.WordUtils;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Transport;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.OrderStatus;

import java.lang.reflect.Field;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getName();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String s) {
        String statusName = WordUtils.capitalize(s.toLowerCase());
        Field[] fields = getClass().getDeclaredFields();
        for (Field field: fields){
            try {
                if (field.getType().equals(Transport.class) && ((OrderStatus) field.get(null)).getName().equals(statusName)){
                    return (OrderStatus) field.get(null);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
