package com.example.springtodo.service;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;

public interface TodoService {

  /**
   * 일정 정보를 받아 일정을 저장하는 메소드
   *
   * @param dto 일정 생성 요청에 포함된 DTO 객체
   * @return 저장된 일정 정보를 가진 응답 DTO 객체
   */
  TodoResponseDto saveTodo(TodoRequestDto dto);

}
