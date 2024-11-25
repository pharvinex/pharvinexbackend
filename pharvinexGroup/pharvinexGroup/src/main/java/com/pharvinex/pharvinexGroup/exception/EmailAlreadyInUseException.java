package com.pharvinex.pharvinexGroup.exception;

public class EmailAlreadyInUseException extends RuntimeException{

    public EmailAlreadyInUseException(String message){
        super(message);
    }
}
