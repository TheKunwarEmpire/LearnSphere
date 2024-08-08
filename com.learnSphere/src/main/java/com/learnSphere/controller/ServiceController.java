package com.learnSphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnSphere.entities.Users;
import com.learnSphere.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class ServiceController {

	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		boolean userStatus = service.checkEmail(user.getEmail());
		if(userStatus == false) {
			service.addUser(user);
			System.out.println("User added");
			return "index";
		}
		else {
			System.out.println("User already exists");
			return "register";
		}
		
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam ("email") String email,
			@RequestParam ("password") String password,
			HttpSession session, Model model) {
		
		if(service.validate(email, password) == true) {
			String role = service.getUserRole(email);
			session.setAttribute("email", email);
			System.out.println( (String) session.getAttribute("email"));
			if(role.equals("Trainer")) {
				return "trainerHome";
			}
			else {
				Users user = service.getUser(email);
				boolean userStatus = user.hasSubscribed();
				model.addAttribute("hasSubscribed", userStatus);
				return "studentHome";
			}
		}
		else {
			return "login";
		}
		
	}
}
