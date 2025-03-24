package com.example.springtodo.repository;

import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;

public interface TodoRepository {

  TodoResponseDto saveTodo(Todos todos);

}
