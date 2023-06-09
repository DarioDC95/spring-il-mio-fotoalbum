package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Picture;
import org.java.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
