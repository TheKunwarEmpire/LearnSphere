package com.learnSphere.services;

import java.util.List;

import com.learnSphere.entities.Comments;

public interface CommentService {
	List<Comments> commentList();
	String addComments(Comments comment);
}
