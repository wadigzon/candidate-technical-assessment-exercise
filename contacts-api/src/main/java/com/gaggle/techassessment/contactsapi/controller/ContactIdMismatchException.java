package com.gaggle.techassessment.contactsapi.controller;

public class ContactIdMismatchException extends RuntimeException{
    public ContactIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
