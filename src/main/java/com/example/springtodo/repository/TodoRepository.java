package com.example.springtodo.repository;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;
import java.util.List;

public interface TodoRepository {

  /**
   * 일정 정보를 DB에 저장하는 메소드
   *
   * @param todos 저장할 일정 정보를 담고 있는 {@link Todos} 객체
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  TodoResponseDto saveTodo(Todos todos);

  /**
   * 모든 일정을 가져오는 메소드
   *
   * @return 저장된 일정 정보를 리스트로 {@link TodoResponseDto} 객체
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
   * 식별자 Id를 가진 일정 정보를 반환하는 메소드
   * <p>
   * 조회된 일정이 없으면 예외를 던짐
   * </p>
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return schedule 테이블에서 조회된 결과와 응답 코드를 포함하는 {@link Todos} 객체
   */
  Todos findTodoByIdOrElseThrow(Long schedule_id);

  /**
   * 식별자 id를 가진 일정 정보를 수정하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @param dto         사용자 요청 객체
   * @return 쿼리의 결과로 변경된 행의 개수
   */
  int updateTodo(Long schedule_id, TodoRequestDto dto);

  /**
   * 식별자 id를 가진 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return 쿼리의 결과로 변경된 행의 개수
   */
  int deleteTodo(Long schedule_id);

  /**
   * 사용자가 입력한 비밀번호가 맞는지 확인하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @param dto         사용자 요청 객체
   * @return 비밀번호가 올바르면 true
   */
  boolean isValidPwd(Long schedule_id, TodoRequestDto dto);

}
