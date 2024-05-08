package com.hatio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.dao.UserRepo;
import com.hatio.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	public boolean checkUser(String email, String password) {

		User user = userRepo.findByEmail(email);
		
		if((user.getPassword()).equals(password)) {
			return true;
		}
		
		return false;
	}

}
