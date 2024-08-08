package com.learnSphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnSphere.entities.Lesson;
import com.learnSphere.services.StudentService;

@Controller
public class StudentController {
	
	 @Autowired
	    private StudentService studentService;

	    @GetMapping("/myLesson")
	    public String getLesson(@RequestParam("lessonId") int lessonId, Model model) {
	        Lesson lesson = studentService.getLession(lessonId);
	        model.addAttribute("lesson", lesson);
	        return "myLesson"; // The view name to display the lesson details
	    }
}
