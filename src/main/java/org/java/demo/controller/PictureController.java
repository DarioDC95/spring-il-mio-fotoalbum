package org.java.demo.controller;

import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/picture")
public class PictureController {
	
	@Autowired
	PictureServ pictureServ;

	@GetMapping("/index")
	public String index() {
		
		return "";
	}
}
