package com.example.springtodo.service;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import java.util.List;

public interface TodoService {

  /**
   * 일정 정보를 받아 일정을 저장하는 메소드
   *
   * @param dto 일정 생성 요청에 포함된 DTO 객체
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  TodoResponseDto saveTodo(TodoRequestDto dto);

  /**
   * 저장된 모든 일정을 조회하는 메소드
   *
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체 리스트
   */
  List<TodoResponseDto> findAllTodos();

//  List<TodoResponseDto> findTodos(LocalDateTime updated_date, String name);

  /**
   * schedule_id를 이용해서 하나의 일정을 조회하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return 하나의 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  TodoResponseDto findTodoById(Long schedule_id);

  /**
   * schedule_id를 가진 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 저장된 일정 id
   */
  void deleteMemo(Long schedule_id);

}
