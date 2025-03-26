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
   * @see #findTodos(TodoRequestDto)
   */
  List<TodoResponseDto> findAllTodos();

  /**
   * 특정 조건에 맞는 일정을 가져오는 메소드
   *
   * @param dto 사용자 요청 객체
   * @return 조건에 해당하는 일정 정보 {@link TodoResponseDto} 객체 리스트
   */
  List<TodoResponseDto> findTodos(TodoRequestDto dto);

  /**
   * schedule_id를 이용해서 하나의 일정을 조회하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return 하나의 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  TodoResponseDto findTodoById(Long schedule_id);

  /**
   * schedule_id를 가진 일정을 수정하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @param dto         사용자 요청 객체
   * @return 수정된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  TodoResponseDto updateTodo(Long schedule_id, TodoRequestDto dto);

  /**
   * schedule_id를 가진 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 저장된 일정 id
   * @param dto         사용자 요청 객체
   */
  void deleteMemo(Long schedule_id, TodoRequestDto dto);

}
