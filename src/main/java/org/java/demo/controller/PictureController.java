package org.java.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.java.demo.pojo.Category;
import org.java.demo.pojo.Picture;
import org.java.demo.serv.CategoryServ;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/picture")
public class PictureController {
	
	@Autowired
	private PictureServ pictureServ;
	
	@Autowired
	private CategoryServ categoryServ;

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
	public String show(Model model, @PathVariable("id") int id) {
		
		Optional<Picture> optPicture = null;
		
		try {
			
			optPicture = pictureServ.findByIdWithCategories(id);
		} catch(NoSuchElementException e) {
			
			String error = "Elemento non trovato";
			List<Picture> pictures = pictureServ.findAll();
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		List<Category> categories = picture.getCategories();
		
		model.addAttribute("picture", picture);
		model.addAttribute("categories", categories);
		
		return "show-picture";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		List<Category> categories = categoryServ.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("picture", new Picture());
		
		return "create-picture";
	}
	
	@PostMapping("/create")
	public String store(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Category> categories = categoryServ.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("picture", picture);
			model.addAttribute("errors", bindingResult);
			
			return "create-picture";
		}
		
		pictureServ.save(picture);
		
		return "redirect:/picture/index";
	}
	
	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		Optional<Picture> optPicture = pictureServ.findById(id);
		
		if(optPicture.isEmpty()) {
			
			String error = "Elemento non trovato";
			List<Picture> pictures = pictureServ.findAll();
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		List<Category> categories = categoryServ.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("picture", picture);
		
		return "edit-picture";
	}
	
	@PostMapping("edit/{id}")
	public String update(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Category> categories = categoryServ.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("picture", picture);
			model.addAttribute("errors", bindingResult);
			
			return "edit-picture";
		}
		
		pictureServ.save(picture);
		
		return "redirect:/picture/index";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		Optional<Picture> optPicture = pictureServ.findById(id);
		
		if(optPicture.isEmpty()) {
			
			String error = "Elemento non trovato";
			List<Picture> pictures = pictureServ.findAll();
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		
		pictureServ.delete(picture);
		
		return "redirect:/picture/index";
	}
}
