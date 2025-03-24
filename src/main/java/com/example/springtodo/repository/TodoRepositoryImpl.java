package com.example.springtodo.repository;

import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

  @Override
  public TodoResponseDto saveTodo(Todos todos) {
    return null;
  }

}
