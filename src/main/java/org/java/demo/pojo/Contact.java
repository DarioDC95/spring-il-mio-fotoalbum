package org.java.demo.pojo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "La Descrizione non deve essere vuota")
	private String email;
	
	@NotBlank(message = "La Descrizione non deve essere vuota")
	@Length(max = 65535)
	private String message;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	public Contact() { }
	public Contact(String email, String message) {
		
		setEmail(email);
		setMessage(message);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
