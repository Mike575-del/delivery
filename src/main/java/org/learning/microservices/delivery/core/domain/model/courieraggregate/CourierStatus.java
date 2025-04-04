package org.learning.microservices.delivery.core.domain.model.courieraggregate;

public enum CourierStatus {

    Free("Free"),Busy("Busy");
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
}
