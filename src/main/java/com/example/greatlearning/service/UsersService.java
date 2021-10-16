package com.example.greatlearning.service;

import com.example.greatlearning.entity.Users;

import java.util.List;
import java.util.Optional;


public interface UsersService {

	void addUser(Users user);

	void updateUser(Users user);

	Optional<Users> getUsersById(Integer id);
	 
	void deleteUsersById(int id);

	List<Users> getAllUsers();
}