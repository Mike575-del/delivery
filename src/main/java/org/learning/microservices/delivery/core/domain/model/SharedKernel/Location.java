package org.learning.microservices.delivery.core.domain.model.SharedKernel;

import java.util.Random;


public class Location {

    public static final Location START_LOCATION = Location.getFromCoordinates(1,1);

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 10;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Location getFromCoordinates(int x, int y){
        if (x >= LOWER_BOUND && y >= LOWER_BOUND && x <= UPPER_BOUND && y <= UPPER_BOUND){
            return new Location(x, y);
        }
        throw new IllegalArgumentException();
    }

    public static Location getRandomLocation(){
        Random random = new Random();
        return getFromCoordinates(random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND,
                random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND);
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Location){
            return x == ((Location) other).x && y == ((Location) other).y;
        }
        return false;
    }

    public int getDistance(Location other){
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

}
