package com.gaggle.techassessment.contactsapi;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ContactsApiLiveTests {
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port +"/api/contacts";
	}		
	@Test
	public void testGetAllContacts() {
        final Response response = RestAssured.get(getRootUrl());
        assertEquals(HttpStatus.OK.value(), response.statusCode());		
	}
}
