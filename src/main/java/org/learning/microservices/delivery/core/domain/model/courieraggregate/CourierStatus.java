package org.learning.microservices.delivery.core.domain.model.courieraggregate;

public class CourierStatus {

    public static CourierStatus Free = new CourierStatus("Free");
    public static CourierStatus Busy = new CourierStatus("Busy");
    private String name;

    public String getName() {
        return name;
    }

    private CourierStatus (String name){
        setName(name);
    }

    private void setName(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name shouldn't be empty or blank");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof CourierStatus){
            return this.name.equals(((CourierStatus) other).name);
        }
        return false;
    }
}
