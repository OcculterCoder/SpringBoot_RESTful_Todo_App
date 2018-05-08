package com.example.springboot.firstwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.springboot.firstwebapp.model.Todo;
import com.example.springboot.firstwebapp.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	@Autowired
	TodoService service;
	
	@RequestMapping(value = "/todoList", method = RequestMethod.GET)
	public String todoList(ModelMap model){
		String ami = (String)model.get("name");
		model.put("todoList", service.retrieveTodos(ami));
		return "listTodo";
	}
	
	@RequestMapping(value = "/addTodo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model){
		model.addAttribute("todo", new Todo(0, (String)model.get("name"), "", new Date(), false));
		return "addTodo";
	}
	
	@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todoo, BindingResult result ){
		if(!result.hasErrors()){
			service.addTodo((String)model.get("name"), todoo.getDesc(), todoo.getTargetDate(), false);
			return "redirect:/todoList";
		}
		return "addTodo";
		
	}
	
	@RequestMapping(value = "/updateTodo", method = RequestMethod.GET)
	public String updateTodoPage(ModelMap model, @RequestParam int id){
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "addTodo";
	}
	
	@RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
		todo.setUser((String)model.get("name"));
		if(!result.hasErrors()){
			service.updateTodo(todo);
			return "redirect:/todoList";
		}
		
		return "addTodo";
	}
	
	@RequestMapping(value = "/deleteTodo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id){
		service.deleteTodo(id);
		return "redirect:/todoList";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
}
