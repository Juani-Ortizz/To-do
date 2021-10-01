package com.todos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todos.entity.Todo;
import com.todos.repository.TodoRepository;

@Service
public class TodoServiceImp implements TodoService {

	@Autowired
	private TodoRepository todoRep;
	
	@Override
	public List<Todo> listTodos() {
		return (List<Todo>) todoRep.findAll();
	}
 
	@Override
	public void saveTodo(Todo todo) {
		todoRep.save(todo);
		
	}

	@Override 
	public Todo searchById(Long id) {
		return todoRep.findById(id).orElse(null);
	}

	@Override
	public void deleteTodo(Long Id) {
		todoRep.deleteById(Id);
		
	}

}
