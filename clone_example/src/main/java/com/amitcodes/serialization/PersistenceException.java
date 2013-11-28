package com.amitcodes.serialization;

public class PersistenceException extends RuntimeException {

    public PersistenceException(String message, Throwable t) {
        super(message, t);
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable t) {
        super(t);
    }
}
