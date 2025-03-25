package com.example.springtodo.service;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;
import com.example.springtodo.repository.TodoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * 일정 관리 서비스 구현 클래스 {@link TodoService} 구현
 */
@Service
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;

  public TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  /**
   * 일정 정보를 받아 일정을 저장하는 메소드
   *
   * @param dto 일정 생성 요청 DTO 객체
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  @Override
  public TodoResponseDto saveTodo(TodoRequestDto dto) {
    Todos todos = new Todos(dto.getName(), dto.getTodo(), dto.getPassword());
    return todoRepository.saveTodo(todos);
  }

  /**
   * 저장된 일정 정보를 모두 출력하는 메소드
   *
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체 리스트
   */
  @Override
  public List<TodoResponseDto> findAllTodos() {
    return todoRepository.findAllTodos();
  }

  /**
   * 식별자 Id를 가진 일정 정보를 출력하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return 하나의 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  @Override
  public TodoResponseDto findTodoById(Long schedule_id) {
    Todos todos = todoRepository.findTodoByIdOrElseThrow(schedule_id);
    return new TodoResponseDto(todos);
  }

  /**
   * 식별자 Id를 가진 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 저장된 일정 id
   * @throws ResponseStatusException 삭제된 행이 없을 경우 NOT FOUND 반환
   */
  @Override
  public void deleteMemo(Long schedule_id) {
    int deletedRow = todoRepository.deleteTodo(schedule_id);
    if (deletedRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dose not exist id: " + schedule_id);
    }
  }

//  @Override
//  public List<TodoResponseDto> findTodos(LocalDateTime updated_date, String name) {
//    return todoRepository.findTodos(updated_date, name);
//  }

}
