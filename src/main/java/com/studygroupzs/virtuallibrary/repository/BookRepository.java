package com.studygroupzs.virtuallibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studygroupzs.virtuallibrary.model.Book;

import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findAllByTitleContainingIgnoreCase(@Param("title") String title);
	public List<Book> findAllByAuthorContainingIgnoreCase(@Param("author") String author);
}
