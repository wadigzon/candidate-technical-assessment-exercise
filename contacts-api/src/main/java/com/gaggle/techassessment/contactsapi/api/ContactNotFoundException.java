package com.gaggle.techassessment.contactsapi.api;

@SuppressWarnings("serial")
public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
