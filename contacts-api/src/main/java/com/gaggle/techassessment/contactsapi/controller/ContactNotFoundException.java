package com.gaggle.techassessment.contactsapi.controller;

@SuppressWarnings("serial")
public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
