package org.spring.rest_registrarionmail.exeption;

public class UserConfirmationException extends RuntimeException {
    public UserConfirmationException(String message) {
        super(message);
    }

    public UserConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }
}
