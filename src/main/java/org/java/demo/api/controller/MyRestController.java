package org.java.demo.api.controller;

import java.util.List;

import org.java.demo.api.controller.dto.ContactResponseDto;
import org.java.demo.api.controller.dto.PictureResponseDto;
import org.java.demo.pojo.Contact;
import org.java.demo.pojo.Picture;
import org.java.demo.serv.ContactServ;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {

	@Autowired
	private PictureServ pictureServ;
	
	@Autowired
	private ContactServ contactServ;
	
	@GetMapping("/picture/index")
	public ResponseEntity<PictureResponseDto> getPizzaWithFilter(@RequestParam(required = false) String title) {
		
		String param;
		if(title == null || title.isBlank()) {
			
			param = "";
		} else {
			
			param = title;
		}
		
		List<Picture> pictures = pictureServ.findPicturesByTitle(true, param);
		
		return new ResponseEntity<>(
					new PictureResponseDto(pictures),
					HttpStatus.OK
				);
	}
	
	@PostMapping("/contact/send")
	public ResponseEntity<ContactResponseDto> storeContact(@Valid @RequestBody(required = true) Contact contact, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			return new ResponseEntity<>(
					new ContactResponseDto(bindingResult), 
					HttpStatus.BAD_REQUEST
				);
		}
		
		contactServ.save(contact);
		
		return new ResponseEntity<>(
					new ContactResponseDto(contact),
					HttpStatus.OK
				);
	}
}
