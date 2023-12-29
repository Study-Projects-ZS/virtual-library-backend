package com.studygroupzs.virtuallibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studygroupzs.virtuallibrary.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findAllByTitleContainingIgnoreCase(String title);
	public List<Book> findAllByAuthorContainingIgnoreCase(String author);
}
