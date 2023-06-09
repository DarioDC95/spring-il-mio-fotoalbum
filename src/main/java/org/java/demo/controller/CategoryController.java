package org.java.demo.controller;

import java.util.List;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private PictureServ pictureServ;

	@GetMapping("/index")
	public String index(Model model) {
		
		List<Category> categories = categoryServ.findAll();
		
		model.addAttribute("categories", categories);
		
		return "index-category";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "create-category";
	}
	
	@PostMapping("/create")
	public String store(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
		
			return "create-category";
		}
		
		categoryServ.save(category);
		
		return "redirect:/category/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		Optional<Category> optCategory = categoryServ.findById(id);
		
		if(optCategory.isEmpty()) {
			
			String error = "Elemento non trovato";
			List<Category> categories = categoryServ.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("error", error);
			
			return "index-category";
		}
		
		Category category = optCategory.get();
		
		model.addAttribute("category", category);
		
		return "edit-category";
	}
	
	@PostMapping("/edit/{id}")
	public String update(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
		
			return "edit-category";
		}
		
		categoryServ.save(category);
		
		return "redirect:/category/index";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		Optional<Category> optCategory = categoryServ.findById(id);
		if(optCategory.isEmpty()) {
			
			String error = "Elemento non trovato";
			List<Category> categories = categoryServ.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("error", error);
			
			return "index-category";
		}
		
		Category category = optCategory.get();
		List<Picture> pictures = category.getPictures();
		
		for (Picture p : pictures) {
			
			p.removeCategory(category);
			pictureServ.save(p);
		}
		
		categoryServ.delete(category);
		
		return "redirect:/category/index";
	}
}
