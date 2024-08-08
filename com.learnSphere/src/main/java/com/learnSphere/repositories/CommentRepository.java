package com.learnSphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnSphere.entities.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

}
