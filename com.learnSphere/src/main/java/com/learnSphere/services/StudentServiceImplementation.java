package com.learnSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entities.Lesson;
import com.learnSphere.repositories.LessonRepository;

@Service
public class StudentServiceImplementation implements StudentService{
	
	@Autowired
	LessonRepository lessonRepo;

	@Override
	public Lesson getLession(int lessonId) {
		return lessonRepo.findById(lessonId).orElse(null);
	}
}
