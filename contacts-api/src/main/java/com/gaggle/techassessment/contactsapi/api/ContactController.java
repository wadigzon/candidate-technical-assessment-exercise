package com.gaggle.techassessment.contactsapi.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gaggle.techassessment.contactsapi.model.Contact;
import com.gaggle.techassessment.contactsapi.repo.ContactRepository;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping
	public Iterable findAll() {
		return contactRepository.findAll();
	}
	
	@GetMapping("/name/{contactName}")
	public List findByName(@PathVariable String contactName) {
		return contactRepository.findByName(contactName);
	}
	
	@GetMapping("/{id}")
	public Contact findOne(@PathVariable Long id) {
		return contactRepository.findById(id).orElseThrow();
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact create(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactRepository.findById(id)
          .orElseThrow();
        contactRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Body updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        if (contact.getId() != id) {
          throw new ContactIdMismatchException(null, null);
        }
        contactRepository.findById(id)
          .orElseThrow();
        return (Body) contactRepository.save(contact);
    }	
}
