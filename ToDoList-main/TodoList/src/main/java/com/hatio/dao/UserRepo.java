package com.hatio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
}
