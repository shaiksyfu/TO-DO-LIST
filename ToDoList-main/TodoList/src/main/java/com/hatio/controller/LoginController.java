package com.hatio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatio.service.UserService;

@Controller
public class LoginController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String getLoginPage() {
		return "login";
	}
	
	@PostMapping("/do_login")
	public String doLogin(@RequestParam("email") String email, 
	                      @RequestParam("password") String password, 
	                      Model model) {
	    boolean isUserExist = userService.checkUser(email, password);
	    if (isUserExist) {
	        return "redirect:/home";
	    } else {
	        model.addAttribute("message", "Invalid email or password.");
	        return "login"; // Return to the login page with an error message
	    }
	}
}
