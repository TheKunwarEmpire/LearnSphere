package com.learnSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entities.Users;
import com.learnSphere.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repos;
	@Override
	public String addUser(Users user) {
		repos.save(user);
		return "User is added successfully";
	}

	@Override
	public boolean checkEmail(String email) {
		if(repos.findByEmail(email) == null){
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean validate(String email, String password) {
		Users user = repos.findByEmail(email);
		if(password.equals(user.getPassword())) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getUserRole(String email) {
		Users user = repos.findByEmail(email);
		return user.getRole();
	}

	@Override
	public Users getUser(String email) {
		return repos.findByEmail(email);
	}

	@Override
	public void updateUser(Users users) {
		repos.save(users);
	}
}
