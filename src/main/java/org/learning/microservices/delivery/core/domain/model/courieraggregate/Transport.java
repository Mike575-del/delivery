package org.learning.microservices.delivery.core.domain.model.courieraggregate;

import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

import java.util.UUID;

public class Transport {

    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 3;

    private final UUID id;
    private String name;
    private int speed;

    public Transport(String name, int speed){
        id = UUID.randomUUID();
        setName(name);
        setSpeed(speed);
    }

    public Transport(UUID id, String name, int speed){
        this.id = id;
        setName(name);
        setSpeed(speed);
    }


    public Location move(Location from, Location to){
        int xDistance = to.getX() - from.getX();
        int yDistance = to.getY() - from.getY();
        int moveX = Math.min(speed, Math.abs(xDistance));
        int moveY = Math.min(Math.abs(yDistance), speed - moveX);

        return Location.getFromCoordinates((int) (from.getX() + moveX*Math.signum((float) xDistance)),
                (int) (from.getY() + moveY*Math.signum((float) yDistance)));

    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Transport) {
            return this.id.equals(((Transport) other).id);
        }
        return false;
    }

    private void setName(String name){
        if (name.isBlank()){
            throw new NullPointerException("Name shouldn't be empty");
        }
        this.name = name;
    }

    private void setSpeed(int speed){
        if (speed < MIN_SPEED || speed > MAX_SPEED){
            throw new IllegalArgumentException("Speed should be in interval from " + MIN_SPEED
                    + " to " + MAX_SPEED);
        }
        this.speed = speed;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
