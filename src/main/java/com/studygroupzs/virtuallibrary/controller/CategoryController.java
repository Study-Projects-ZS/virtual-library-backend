package com.studygroupzs.virtuallibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studygroupzs.virtuallibrary.model.Category;
import com.studygroupzs.virtuallibrary.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id){
		return categoryRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{description}")
	public ResponseEntity<Category> getByDescription(@PathVariable String description){
		return categoryRepository.findByDescription(description)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Category> postCategory(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Category> putCategory(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.save(category));
	}
}