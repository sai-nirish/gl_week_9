package com.example.greatlearning.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.example.greatlearning.entity.Users;
import com.example.greatlearning.repository.UsersRepository;
import com.example.greatlearning.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public void addUser(Users user) {
		usersRepository.save(user);
	}

	@Override
	public Optional<Users> getUsersById(Integer id) {
	    return usersRepository.findById(id);
	}

	@Override
	public void deleteUsersById(int id) {
		usersRepository.deleteById(id);
	}

	@Override
	public List<Users> getAllUsers() {
	    return (List<Users>) usersRepository.findAll();
	}

	@Override
	public void updateUser(Users user) {
		boolean exist = usersRepository.existsById(user.getId());
		if (exist) {
			usersRepository.save(user);
		} else {
			throw new RuntimeException("There is not user with id " + user.getId());
		}
	}

}