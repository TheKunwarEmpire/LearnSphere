package com.learnSphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entities.Course;
import com.learnSphere.entities.Lesson;
import com.learnSphere.repositories.CourseRepository;
import com.learnSphere.repositories.LessonRepository;

@Service
public class TrainerServiceImplementation implements TrainerService {

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	LessonRepository lessonRepo;
	
	@Override
	public String addCourse(Course course) {
		courseRepo.save(course);
		return "Course is created";
	}

	@Override
	public boolean checkCourse(int courseId) {
		if(courseRepo.findByCourseId(courseId) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public String addLesson(Lesson lesson) {
		lessonRepo.save(lesson);
		return "Lesson is added";
	}

	@Override
	public Course getCourse(int courseId) {
		return courseRepo.findByCourseId(courseId);
	}

	@Override
	public List<Course> courseList() {
		return courseRepo.findAll();
	}

	@Override
	public void update(Course c) {
		courseRepo.save(c);
	}

}
