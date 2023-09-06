package com.usermanageent.usermanagement.exception;

public class NotFoundException extends ApplicationException{

    public NotFoundException() {
        super("Entity not found!");
    }
}
