package com.learnSphere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnSphere.entities.Course;
import com.learnSphere.entities.Lesson;
import com.learnSphere.services.TrainerService;



@Controller
public class TrainerController {
	
	@Autowired
	TrainerService service;
	
	@PostMapping("/createCourse")
	public String createCourse(@ModelAttribute Course course) {
		if(service.checkCourse(course.getCourseId()) == false) {
			service.addCourse(course);
			System.out.println("Course created");
			return "trainerHome";
		}
		else {
			System.out.println("Course already exists");
			return "createCourse";
		}
	}
	
	@GetMapping("/createLesson")
	public String createLesson(Model model) {
		List<Course> courseList = service.courseList();
		System.out.println("Create Lesson: " + courseList);
		model.addAttribute("courses", courseList);
		return "createLesson";
	}
	
	@PostMapping("/addLesson")
	public String addLesson(@ModelAttribute Lesson lesson, @RequestParam int courseId) {
		
		Course courseObj = service.getCourse(courseId);
		lesson.setCourse(courseObj);
		service.addLesson(lesson);
		System.out.println("Course Obj: " + courseObj);
		courseObj.getLessons().add(lesson);
		service.update(courseObj);
		return "trainerHome";
	}

	@GetMapping("/viewAllCourses")
	public String viewAllCourses(Model model) {
		List<Course> courseList = service.courseList();
		model.addAttribute("coursesList", courseList);
		return "courses";
	}
	
}
