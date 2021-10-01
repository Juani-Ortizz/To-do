package com.todos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todos.entity.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Long> {

	

	
}
