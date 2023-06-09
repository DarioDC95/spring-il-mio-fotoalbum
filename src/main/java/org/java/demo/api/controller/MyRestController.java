package org.java.demo.api.controller;

import java.util.List;

import org.java.demo.api.controller.dto.PictureResponseDto;
import org.java.demo.pojo.Picture;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {

	@Autowired
	private PictureServ pictureServ;
	
	@GetMapping("/picture/index")
	public ResponseEntity<PictureResponseDto> getPizzaWithFilter(@RequestParam(required = false) String title) {
		
		String param;
		if(title == null || title.isBlank()) {
			
			param = "";
		} else {
			
			param = title;
		}
		
		List<Picture> pictures = pictureServ.findPicturesByTitle(param);
		
		return new ResponseEntity<>(
				new PictureResponseDto(pictures),
				HttpStatus.OK
				);
	}
}
