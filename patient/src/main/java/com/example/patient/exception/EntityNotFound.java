package com.example.patient.exception;

public class EntityNotFound extends RuntimeException{

    public EntityNotFound(){}


    public EntityNotFound(String message){
        super(message);
    }
}
