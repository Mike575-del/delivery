package org.learning.microservices.delivery.infrastructure.exceptions;

public class InvalidStatusException extends RuntimeException{

    public InvalidStatusException(String message){
        super(message);
    }

}
