package org.learning.microservices.delivery.core.domain.model.orderaggregate;

import org.apache.commons.text.WordUtils;

public class OrderStatus {

    public static OrderStatus Created = new OrderStatus("created");
    public static OrderStatus Assigned = new OrderStatus("ASSIGNED");
    public static OrderStatus Completed = new OrderStatus("Completed");

    private String name;


    private OrderStatus(String name) {
        setName(name);
    }

    private void setName(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name shouldn't be null or blank!");
        }
        this.name = WordUtils.capitalize(name.toLowerCase());
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof OrderStatus){
            return this.name.equals(((OrderStatus) other).name);
        }
        return false;
    }
}
