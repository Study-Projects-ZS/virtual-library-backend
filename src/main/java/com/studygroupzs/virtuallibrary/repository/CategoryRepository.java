package com.studygroupzs.virtuallibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studygroupzs.virtuallibrary.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Optional<Category> findByDescription(String description);
}
