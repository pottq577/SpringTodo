package com.example.springtodo.repository;

import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;

public interface TodoRepository {

  /**
   * 일정 정보를 DB에 저장하는 메소드
   *
   * @param todos 저장할 일정 정보를 담고 있는 {@link Todos} 객체
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  TodoResponseDto saveTodo(Todos todos);

}
