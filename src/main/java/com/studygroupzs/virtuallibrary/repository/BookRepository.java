package com.studygroupzs.virtuallibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studygroupzs.virtuallibrary.model.Book;

import feign.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findAllByTitleContaingIgnoreCase(@Param("title") String title);
	public List<Book> findAllByAuthorContaingIgnoreCase(@Param("title") String title);
}
