package com.studygroupzs.virtuallibrary.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
