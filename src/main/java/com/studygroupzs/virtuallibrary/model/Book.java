package com.studygroupzs.virtuallibrary.model;

import java.math.BigDecimal;
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
	
	@NotBlank(message = "The isbn is mandatory")
	private String isbn;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Integer yearOfPublication;
	
	private String description;
	
	private BigDecimal rating;
	
	private String cover;

}
