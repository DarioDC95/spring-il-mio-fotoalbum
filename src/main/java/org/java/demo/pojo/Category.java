package org.java.demo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String name;
	
	@JsonIgnoreProperties("categories")
	@ManyToMany(mappedBy = "categories")
	private List<Picture> pictures;
	
	public Category() { }
	public Category(String name) {
		
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		
//		if (!(obj instanceof Category)) return false;
//		
//		Category cObj = (Category) obj;
//		
//		return getId() == cObj.getId();
//	}
//	@Override
//	public int hashCode() {
//		
//		return getId();
//	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName();
	}
}
