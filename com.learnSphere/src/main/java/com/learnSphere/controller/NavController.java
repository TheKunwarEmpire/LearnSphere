package com.learnSphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnSphere.entities.Users;
import com.learnSphere.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class NavController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	@GetMapping("register")
	public String register() {
		return "register";
	}
	@GetMapping("addCourse")
	public String addCourse() {
		return "createCourse";
	}
	@GetMapping("addLesson")
	public String addLesson() {
		return "createLesson";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	@GetMapping("/myCourses")
	public String getMyCourses(HttpSession session, Model model) {
	    String email = (String) session.getAttribute("email");
	    Users user = userService.getUser(email);
	    
	    if (user.hasSubscribed()) {
	        model.addAttribute("hasSubscribed", true);
	        
	        model.addAttribute("coursesList", user.getCourses());
	    } else {
	        model.addAttribute("hasSubscribed", false);
	    }
	    
	    return "myCourses"; // Thymeleaf template name
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}
	@GetMapping("/home")
	public String home() {
		return "index";
	}
}
