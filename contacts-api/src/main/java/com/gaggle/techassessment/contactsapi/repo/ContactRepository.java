package com.gaggle.techassessment.contactsapi.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.gaggle.techassessment.contactsapi.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findByName(String name);
}
