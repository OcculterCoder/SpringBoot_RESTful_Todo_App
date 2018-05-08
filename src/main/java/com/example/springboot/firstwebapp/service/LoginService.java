package com.example.springboot.firstwebapp.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	public boolean validateUser(String userID, String password){
		return userID.equals("Hello") && password.equals("123");
	}
}
