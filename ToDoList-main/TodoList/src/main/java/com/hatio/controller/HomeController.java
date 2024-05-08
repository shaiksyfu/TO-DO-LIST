package com.hatio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {


	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("Home controller");
		return "home";
	}

}
