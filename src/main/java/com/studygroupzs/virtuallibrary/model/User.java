package com.studygroupzs.virtuallibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "The attribute name is mandatory")
	private String name;
	
	@NotBlank(message = "The attribute username is mandatory")
	@Email
	private String username;
	
	@NotBlank(message = "The attribute password is mandatory")
	@Size(min = 8, message = "the password must be at least 8 characters long")
	private String passaword;
}
