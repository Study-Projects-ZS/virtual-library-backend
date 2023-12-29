package com.studygroupzs.virtuallibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studygroupzs.virtuallibrary.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username);
	public List<User> findByNameContainingIgnoreCase(String name);
}
