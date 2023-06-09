package org.java.demo.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	
	@GetMapping("/")
	public String homePage() {
		
		return "home-page";
	}
}
