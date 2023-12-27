package com.studygroupzs.virtuallibrary.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studygroupzs.virtuallibrary.model.User;
import com.studygroupzs.virtuallibrary.model.UserLogin;
import com.studygroupzs.virtuallibrary.repository.UserRepository;
import com.studygroupzs.virtuallibrary.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		return userRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<User> getByName(@PathVariable String name) {
		return userRepository.findByName(name)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getByUsername(@PathVariable String username) {
		return userRepository.findByUsername(username)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> postUser(@RequestBody @Valid User user) {

		return userService.createUser(user)
				.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
				.orElse(ResponseEntity.badRequest().build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> authenticateUser(@RequestBody @Valid Optional<UserLogin> userLogin){
		return userService.authenticateUser(userLogin)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> putUser(@RequestBody @Valid User user){
		return userService.updateUser(user)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
