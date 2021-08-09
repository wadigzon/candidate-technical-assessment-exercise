package com.gaggle.techassessment.contactsapi.api;

public class ContactIdMismatchException extends RuntimeException{
    public ContactIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
