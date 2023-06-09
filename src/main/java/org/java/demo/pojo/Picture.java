package org.java.demo.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il titolo non deve essere vuoto")
	private String title;
	
	@NotBlank(message = "La Descrizione non deve essere vuota")
	private String description;
	
	@NotBlank(message = "L'url non deve essere vuoto")
	private String url;
	
	@NotNull(message = "La visibilità non deve essere vuota")
	private Boolean visible;
	
	@JsonIgnoreProperties("pictures")
	@ManyToMany
	private List<Category> categories;
	
	public Picture() { }
	public Picture(String title, String description, String url, Boolean visible, Category...categories) {
		
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setCategories(categories);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean isVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	@JsonSetter
	public void setCategories(Category[] categories) {
		setCategories(Arrays.asList(categories));
	}
	public void removeCategory(Category category) {
		getCategories().remove(category);
	}
	
	public boolean containsCategory(Category category) {
		return getCategories() != null ? getCategories().contains(category) : false;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle()
				+ "\nDescrizione: " + getDescription() 
				+ "\nFoto: " + getUrl()
				+ "\nVisibilità: " + isVisible();
	}
}
