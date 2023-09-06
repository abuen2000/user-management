package com.usermanageent.usermanagement.exception;

public class ApplicationException extends RuntimeException{

    public ApplicationException() {
        super("Encountered an error during the process. Please contact our support team!");
    }

    public ApplicationException(String message) {
        super(message);
    }
}
