package com.learnSphere.services;

import java.util.List;

import com.learnSphere.entities.Course;
import com.learnSphere.entities.Lesson;

public interface TrainerService {
	public String addCourse(Course course);
	public boolean checkCourse(int courseId);
	public String addLesson(Lesson lesson);
	Course getCourse(int courseId);
	public List<Course> courseList();
	public void update(Course c);
}
