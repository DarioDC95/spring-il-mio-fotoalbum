package org.java.demo.repo;

import java.util.List;

import org.java.demo.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Integer> {
	
	public List<Picture> findByTitleContaining(String title);
	public List<Picture> findByTitleContainingAndUserId(String title, int id);
	public List<Picture> findByUserId(int id);
}
