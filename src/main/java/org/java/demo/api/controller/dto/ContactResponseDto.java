package org.java.demo.api.controller.dto;

import org.java.demo.pojo.Contact;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ContactResponseDto {

	private Contact contact;
	
	@JsonSerialize(using = BindingResultSerializer.class)
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
