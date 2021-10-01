package com.todos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.todos.entity.Todo;
import com.todos.service.TodoService;

@Controller
@RequestMapping("/views/todos")
public class TodoController {
	 
	@Autowired
	private TodoService todoSer;
	  
	@GetMapping("/") 
	public String listTodo(Model model) {
		List<Todo> listTodos = todoSer.listTodos();
		model.addAttribute("title", "To do!");  
		model.addAttribute("todos", listTodos);
		return "/views/todos/show";  
	}   
	 
	@GetMapping("/create")
	public String create(Model model) {  
		
		Todo todo = new Todo();
		
		model.addAttribute("title", "Form: New To do");
		model.addAttribute("todo", todo);
		
		return "/views/todos/frmCreate";
	}
	
	@PostMapping("/save") 
	public String saveTodo(@Valid @ModelAttribute Todo todo,BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("title", "Form: New To do");
			model.addAttribute("todo", todo);
			System.out.println("Error");
			
			return "/views/todos/frmCreate"; 
					
		} 
		
		todoSer.saveTodo(todo);
		System.out.println("To do Saved");
		
		return "redirect:/views/todos/";
	}
	 
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long idTodo, Model model) {  
		
		Todo todo= null;
		
		if (idTodo>0) {
			todo=  todoSer.searchById(idTodo);
			if (todo==null) {
				System.out.println("Id doesnt exist");
				return "redirect:/views/todos/"; 
			}
		}else {
			System.out.println("Id error");
			return "redirect:/views/todos/"; 
		} 
		
		
		
		model.addAttribute("title", "new To do");
		model.addAttribute("todo", todo);
		
		return "/views/todos/frmCreate"; 
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTodo(@PathVariable("id") Long idTodo) {  
		
Todo todo= null;
		
		if (idTodo>0) {
			todo=  todoSer.searchById(idTodo);
			if (todo==null) {
				System.out.println("Id doesnt exist");
				return "redirect:/views/todos/"; 
			}
		}else { 
			System.out.println("Id error");
			return "redirect:/views/todos/"; 
		} 
		
		todoSer.deleteTodo(idTodo);
		System.out.println("delete succesfull");
		 
		return "redirect:/views/todos/"; 
	}
	 
	 
 
}  
 