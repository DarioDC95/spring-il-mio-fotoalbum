package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Category;
import org.java.demo.pojo.Picture;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/picture")
public class PictureController {
	
	@Autowired
	PictureServ pictureServ;

	@GetMapping("/index")
	public String index(Model model) {
		
		List<Picture> pictures = pictureServ.findAll();
		model.addAttribute("pictures", pictures);
		
		return "index-picture";
	}
	
	@PostMapping("/by/title")
	public String getPizzasByName(Model model, @RequestParam(required = false) String title) {
		
		List<Picture> pictures = pictureServ.findPicturesByTitle(title);
		
		model.addAttribute("pictures", pictures);
		
		return "index-picture";
	}
	
	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable(required = true) int id) {
		
		Optional<Picture> optPictures = pictureServ.findByIdWithCategories(id);
		
		if(optPictures.isEmpty()) {
			
			String error = "Elemento non trovato";
			
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPictures.get();
		List<Category> categories = picture.getCategories();
		
		model.addAttribute("picture", picture);
		model.addAttribute("categories", categories);
		
		return "show-picture";
	}
}
