package com.studygroupzs.virtuallibrary.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "The title is mandatory")
	private String title;
	
	@NotBlank(message = "The author name is mandatory")
	private String author;
	
	private String isbn;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfPublication;
	
	@Column(columnDefinition = "integer default 0")
	private int numberOfLikes;
	
	@ManyToOne
	@JsonIgnoreProperties("books")
	private Category category;
	
	@ManyToMany(mappedBy = "books")
    private Set<User> users = new HashSet<>();

}
