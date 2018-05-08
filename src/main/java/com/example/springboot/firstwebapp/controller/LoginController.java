package com.example.springboot.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.springboot.firstwebapp.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(ModelMap model){
		model.put("name", getLoggedInUserName());
		return "welcome";
	}
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String welcomeMessage(@RequestParam String name, @RequestParam String password, ModelMap model){
		
		if(service.validateUser(name, password)){
			model.put("name", name);
			model.put("password", password);
			return "welcome";
		}else{
			model.put("invalid", "Sorry you entered wrong User Id or Password");
			return "login";
		}
		
		
	}*/
	
}
