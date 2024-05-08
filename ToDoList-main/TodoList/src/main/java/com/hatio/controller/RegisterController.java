package com.hatio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatio.dao.UserRepo;
import com.hatio.entity.User;

@Controller
public class RegisterController {

	@Autowired
	UserRepo userRepo;

	@GetMapping("/register")
	public String getRegisterPage() {
		return "signUp";
	}

	@PostMapping("/do_register")
	public String doRegister(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("phoneNo") long phoneNo,
			@RequestParam("confirm_password") String confirmPassword, Model model) {
		User existingUser = userRepo.findByEmail(email);

		if (existingUser != null) {
			model.addAttribute("message", "User with this email already exists.");
			return "signUp"; // Return the registration form again with the error message
		}

		if (!password.equals(confirmPassword)) {
			model.addAttribute("message", "Passwords don't match.");
			return "signUp"; // Return the registration form again with the error message
		}

		// Passwords match and user doesn't exist, proceed with registration
		User user = new User(name, email, password, phoneNo);
		User save = userRepo.save(user);
		if(save != null) {
			model.addAttribute("registered_succesfully", "Registered successfully");
			System.out.println("Successfull");
			return "login"; // Redirect to the home page after successful registration
		}else {
			model.addAttribute("Register_Issue", "Something went wrong while registration");
			return "signUp";
		}
		
	}

}
