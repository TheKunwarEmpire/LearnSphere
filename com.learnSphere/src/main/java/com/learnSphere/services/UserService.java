package com.learnSphere.services;

import com.learnSphere.entities.Users;


public interface UserService {
	String addUser(Users user);
	boolean checkEmail(String email);
	boolean validate(String email, String password);
	String getUserRole(String email);
	Users getUser(String email);
	void updateUser(Users users);
}
