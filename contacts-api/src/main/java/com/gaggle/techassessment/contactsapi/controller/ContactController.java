package com.gaggle.techassessment.contactsapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gaggle.techassessment.contactsapi.model.Contact;
import com.gaggle.techassessment.contactsapi.service.ContactService;

@RestController
@EnableWebMvc
@RequestMapping("/api/contacts")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public Iterable findAll() {
		return contactService.findAll();
	}
	
	@GetMapping("/name/{contactName}")
	public List<Contact> findByExactName(@PathVariable String contactName) {
		return contactService.findByExactName(contactName); 
	}
	
	@GetMapping("/{id}")
	public Contact findOne(@PathVariable Long id) {
		return contactService.findById(id);
	}
	
	@GetMapping("/namesearch/{searchItem}")
	public List searchByName(@PathVariable String searchItem) {
		return contactService.searchByName(searchItem);
	}

    @GetMapping("/count")
    public Long count() {
        return contactService.getCount();
    }	

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact create(@RequestBody Contact contact) {
		var list = contactService.findByExactName(contact.getName());
		var count = contactService.getCount();
		// insert only if no records found with that name, 
		// otherwise return that (existing) record
        return list.size() == 0 ? contactService.save(contact) : list.get(0);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	var contact = contactService.findById(id);
    	contactService.deleteById(contact.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contactDetails, @PathVariable Long id) {
    	// find that contact
    	var contact = contactService.findById(id);
    	// set the new name
    	contact.setName(contactDetails.getName());
    	// save it to updated contact
    	final Contact updatedContact = contactService.save(contact);
    	// return it
        return ResponseEntity.ok(updatedContact); 
    }	
}
