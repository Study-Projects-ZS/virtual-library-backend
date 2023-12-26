package com.studygroupzs.virtuallibrary.model;

import lombok.Data;

@Data
public class UserLogin {

	private Long id;
	private String name;
	private String username;
	private String password;
	private String token;
}
