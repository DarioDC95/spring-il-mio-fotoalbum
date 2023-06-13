package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.pojo.Picture;
import org.java.demo.repo.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PictureServ {
	
	@Autowired
	private PictureRepo pictureRepo;
	
	public List<Picture> findAll() {
		
		return pictureRepo.findAll();
	}
	public Optional<Picture> findById(int id) {
		
		return pictureRepo.findById(id);
	}
	public Picture save(Picture picture) {
		
		return pictureRepo.save(picture);
	}
	public List<Picture> findPicturesByTitle(String title) {
		
		return pictureRepo.findByTitleContaining(title);
	}
	public List<Picture> findPicturesByTitle(String title, int id) {
		
		return pictureRepo.findByTitleContainingAndUserId(title, id);
	}
	public List<Picture> findPicturesByUserId(int user) {
		
		return pictureRepo.findByUserId(user);
	}
	public List<Picture> findPicturesByTitle(boolean visible, String title) {
		
		return pictureRepo.findByVisibleAndTitleContaining(visible, title);
	}
	@Transactional
	public Optional<Picture> findByIdWithCategories(int id) {
		
		Optional<Picture> picture = pictureRepo.findById(id);
		Hibernate.initialize(picture.get().getCategories());
		
		return picture;
	}
	public void delete(Picture picture) {
		
		pictureRepo.delete(picture);
	}
}
