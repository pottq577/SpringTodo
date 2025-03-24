package com.example.springtodo.service;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;

public interface TodoService {

  TodoResponseDto saveTodo(TodoRequestDto dto);

}
