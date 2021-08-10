package com.gaggle.techassessment.contactsapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaggle.techassessment.contactsapi.controller.ContactNotFoundException;
import com.gaggle.techassessment.contactsapi.model.Contact;
import com.gaggle.techassessment.contactsapi.repo.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> findAll() {
	    var it = contactRepository.findAll();
	    var users = new ArrayList<Contact>();
	    it.forEach(e -> users.add(e));
	    return users;
	}	
	
	public List<Contact> findByExactName(String name) {
	    var it = contactRepository.findAll();
	    var contacts = new ArrayList<Contact>();
	    String lname = name.toLowerCase();
	    it.forEach(e -> {
	    	if (e.getName().toLowerCase().contains(lname)) {
		    	contacts.add(e);
	    	}
	    });
	    return contacts;
	}	
	
	public Contact findById(long id) {
		return contactRepository.findById(id).orElseThrow(ContactNotFoundException::new);
	}
	
	public List<Contact> searchByName(String nameSearch) {
		// get the iterator object
		var it = contactRepository.findAll();
		// new empty list
		var contacts = new ArrayList<Contact>();
		// non-case sensitive
		var search = nameSearch.toLowerCase();
		it.forEach(e -> {
			// name of current element
			String name = e.getName().toLowerCase();
			// if searchItem is contained within name
			if (name.contains(search)) {
				contacts.add(e);
			}
		});
		return contacts;
	}
	
	public long getCount() {
		return contactRepository.count();
	}
	
	public Contact save(Contact contact) { 
		return contactRepository.save(contact);
	}
	
	public void deleteById(long id) {
		contactRepository.deleteById(id);
	}
}
