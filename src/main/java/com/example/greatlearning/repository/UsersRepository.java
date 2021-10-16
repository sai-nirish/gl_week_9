package com.example.greatlearning.repository;

import com.example.greatlearning.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

}
