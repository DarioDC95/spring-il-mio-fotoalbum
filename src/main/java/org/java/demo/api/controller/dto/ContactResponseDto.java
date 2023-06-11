package org.java.demo.api.controller.dto;

import org.java.demo.pojo.Contact;
import org.springframework.validation.BindingResult;

public class ContactResponseDto {

	private Contact contact;
	
	private BindingResult bindingResult;
	
	public ContactResponseDto(Contact contact) {
		setContact(contact);
	}
	public ContactResponseDto(BindingResult bindingResult) {
		setBindingResult(bindingResult);
	}
	public ContactResponseDto(Contact contact, BindingResult bindingResult) {
		setContact(contact);
		setBindingResult(bindingResult);
	}

	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public BindingResult getBindingResult() {
		return bindingResult;
	}
	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
}
