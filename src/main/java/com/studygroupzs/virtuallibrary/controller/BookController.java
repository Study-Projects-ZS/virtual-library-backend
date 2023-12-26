package com.studygroupzs.virtuallibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studygroupzs.virtuallibrary.model.Book;
import com.studygroupzs.virtuallibrary.repository.BookRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@CrossOrigin("/*")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAll(){
		return ResponseEntity.ok(bookRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id){
		return bookRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build()); // http status not found
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Book>> getByTitle(@PathVariable String title){
		return ResponseEntity.ok(bookRepository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@GetMapping("/author/{author}")
	public ResponseEntity<List<Book>> getByAuthor(@PathVariable String title){
		return ResponseEntity.ok(bookRepository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> registerBook(@Valid @RequestBody Book book){
		return ResponseEntity.status(HttpStatus.CREATED) // ResponseEntity.created().body...
				.body(bookRepository.save(book));
	}
	
}
