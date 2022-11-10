package com.glearning.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glearning.student.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
		
}
