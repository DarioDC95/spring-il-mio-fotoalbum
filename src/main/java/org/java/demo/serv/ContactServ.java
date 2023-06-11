package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Contact;
import org.java.demo.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServ {

	@Autowired
	private ContactRepo contactRepo;
	
	public List<Contact> findAll() {
		
		return contactRepo.findAll();
	}
	public Optional<Contact> findById(int id) {
		
		return contactRepo.findById(id);
	}
	public Contact save(Contact contact) {
		
		return contactRepo.save(contact);
	}
	public void delete(Contact contact) {
		
		contactRepo.delete(contact);
	}
}
