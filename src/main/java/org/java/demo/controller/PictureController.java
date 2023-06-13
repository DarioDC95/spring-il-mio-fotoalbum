package org.java.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.java.demo.auth.pojo.User;
import org.java.demo.pojo.Category;
import org.java.demo.pojo.Picture;
import org.java.demo.serv.CategoryServ;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public String index(Model model, Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		
		List<Picture> pictures = null;
		
		//si può fare anche così
		/*boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));*/
		if(user.getRoles().stream().map(a -> a.getName()).collect(Collectors.toList()).contains("SUPER_ADMIN")) {
			
			pictures = pictureServ.findAll();
		} else {
			
			int userId = user.getId();
			pictures = pictureServ.findPicturesByUserId(userId);
		}
		model.addAttribute("pictures", pictures);
		
		return "index-picture";
	}
	
	@PostMapping("/by/title")
	public String getPizzasByName(Model model, Authentication authentication, @RequestParam(required = false) String title) {
		
		User user = (User) authentication.getPrincipal();
		
		List<Picture> pictures = null;
		
		if(user.getRoles().stream().map(a -> a.getName()).collect(Collectors.toList()).contains("ADMIN")) {
			
			int userId = user.getId();
			pictures = pictureServ.findPicturesByTitle(title, userId);
		} else {
			
			pictures = pictureServ.findPicturesByTitle(title);
		}
		
		model.addAttribute("pictures", pictures);
		
		return "index-picture";
	}
	
	@GetMapping("/show/{id}")
	public String show(Model model, Authentication authentication, @PathVariable("id") int id) {
		
		User user = (User) authentication.getPrincipal();
		int userId = user.getId();
		List<Picture> pictures = null;
		
		Optional<Picture> optPicture = null;
		try {
			
			optPicture = pictureServ.findByIdWithCategories(id);
		} catch(NoSuchElementException e) {
			
			String error = "Elemento non trovato";
			pictures = pictureServ.findPicturesByUserId(userId);
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		pictures = pictureServ.findPicturesByUserId(userId);
		if(pictures.contains(picture) == false) {
			
			return "redirect:/picture/index";
		}
		
		List<Category> categories = picture.getCategories();
		
		model.addAttribute("picture", picture);
		model.addAttribute("categories", categories);
		
		return "show-picture";
	}
	
	@GetMapping("/create")
	public String create(Model model, Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		int userId = user.getId();
		
		List<Category> categories = categoryServ.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("picture", new Picture());
		model.addAttribute("userId", userId);
		
		return "create-picture";
	}
	
	@PostMapping("/create")
	public String store(Model model, Authentication authentication, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Category> categories = categoryServ.findAll();
			User user = (User) authentication.getPrincipal();
			int userId = user.getId();
			
			model.addAttribute("categories", categories);
			model.addAttribute("picture", picture);
			model.addAttribute("userId", userId);
			model.addAttribute("errors", bindingResult);
			
			return "create-picture";
		}
		
		pictureServ.save(picture);
		
		return "redirect:/picture/index";
	}
	
	/* route edit just for SUPER_ADMIN to circumvent the @Valid since I want render inaccessible the inputs of the other 
	 * fields of the form from the inspection too*/
	@GetMapping("edit/{id}/super_admin")
	public String editSuperAdmin(Model model, @PathVariable("id") int id) {
		
		List<Picture> pictures = pictureServ.findAll();
		Optional<Picture> optPicture = pictureServ.findById(id);
		
		if(optPicture.isEmpty()) {
			
			String error = "Elemento non trovato";
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		
		model.addAttribute("picture", picture);
		
		return "edit-picture";
	}
	
	@PostMapping("edit/{id}/super_admin")
	public String updateSuperAdmin(Model model, @PathVariable("id") int id, @RequestParam(required = true) Boolean visible) {
		
		Optional<Picture> optPicture = pictureServ.findById(id);
		
		Picture picture = optPicture.get();
		picture.setVisible(visible);
		
		pictureServ.save(picture);
		
		return "redirect:/picture/index";
	}
	//end custom edit for super_admin
	
	@GetMapping("edit/{id}")
	public String edit(Model model, Authentication authentication, @PathVariable("id") int id) {
		
		User user = (User) authentication.getPrincipal();
		Integer userId = user.getId();
		List<Picture> pictures = pictureServ.findPicturesByUserId(userId);
		
		Optional<Picture> optPicture = pictureServ.findById(id);
		
		if(optPicture.isEmpty()) {
			
			String error = "Elemento non trovato";
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		
		if(pictures.contains(picture) == false) {
			
			return "redirect:/picture/index";
		}
		
		List<Category> categories = categoryServ.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("picture", picture);
		model.addAttribute("userId", userId);
		
		return "edit-picture";
	}
	
	@PostMapping("edit/{id}")
	public String update(Model model, Authentication authentication, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {
		
		User user = (User) authentication.getPrincipal();
		Integer userId = user.getId();
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Category> categories = categoryServ.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("picture", picture);
			model.addAttribute("userId", userId);
			model.addAttribute("errors", bindingResult);
			
			return "edit-picture";
		}
		
		pictureServ.save(picture);
		
		return "redirect:/picture/index";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, Authentication authentication, @PathVariable("id") int id) {
		
		User user = (User) authentication.getPrincipal();
		int userId = user.getId();
		List<Picture> pictures = pictureServ.findPicturesByUserId(userId);
		
		Optional<Picture> optPicture = pictureServ.findById(id);
		
		if(optPicture.isEmpty()) {
			
			String error = "Elemento non trovato";
			
			model.addAttribute("pictures", pictures);
			model.addAttribute("error", error);
			
			return "index-picture";
		}
		
		Picture picture = optPicture.get();
		if(pictures.contains(picture) == false) {
			
			return "redirect:/picture/index";
		}
		
		pictureServ.delete(picture);
		
		return "redirect:/picture/index";
	}
}
