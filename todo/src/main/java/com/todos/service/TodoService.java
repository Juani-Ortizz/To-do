package com.todos.service;

import java.util.List;

import com.todos.entity.Todo;

public interface TodoService {

	public List<Todo> listTodos();
	public void saveTodo(Todo todo);
	public Todo searchById(Long id);
	public void deleteTodo(Long Id);
	
	
	
}
