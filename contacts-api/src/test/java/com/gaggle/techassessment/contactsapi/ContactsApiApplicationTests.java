package com.gaggle.techassessment.contactsapi;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaggle.techassessment.contactsapi.controller.ContactController;
import com.gaggle.techassessment.contactsapi.model.Contact;
import com.gaggle.techassessment.contactsapi.service.ContactService;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.runners.MethodSorters;

@ContextConfiguration(classes= {ContactController.class, ContactService.class})
@WebMvcTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ContactsApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private ContactService contactService;
    
    // insert mock contacts
    @Test
    public void test1() throws Exception {
    	Contact mock1 = new Contact();
    	mock1.setName("Wadigzon Diaz-Wong");
    	Contact mock2 = new Contact();
    	mock2.setName("Wadsui Diaz-Alvarado");
    	Contact mock3 = new Contact();
    	mock3.setName("Susana Diaz-Alvarado");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/contacts")
                .content(asJsonString(mock1))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();    	
        String resultSS = result.getResponse().getContentAsString();
        assertNotNull(resultSS);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/api/contacts")
                .content(asJsonString(mock2))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();    	
        resultSS = result.getResponse().getContentAsString();
        assertNotNull(resultSS);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/api/contacts")
                .content(asJsonString(mock3))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();    	
        resultSS = result.getResponse().getContentAsString();
        assertNotNull(resultSS);
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/contacts").accept(
				MediaType.APPLICATION_JSON);
    	result = mockMvc.perform(requestBuilder)
    			.andExpect(status().isOk())
    			.andReturn();
    	System.out.println(result.getResponse().getContentAsString());        
    }
    
    // list inserted contacts
    @Test 
    public void test2() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/contacts").accept(
				MediaType.APPLICATION_JSON);
    	MvcResult result = mockMvc.perform(requestBuilder)
    			.andExpect(status().isOk())
    			.andReturn();
    	System.out.println(result.getResponse().getContentAsString());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
