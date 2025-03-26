package com.example.springtodo.service;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;
import com.example.springtodo.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * 일정 관리 서비스 구현 클래스 {@link TodoService} 구현
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;

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
   * @return 저장된 일정 정보를 포함하는 {@link TodoResponseDto} 객체 리스트
   * @see #findTodos(TodoRequestDto)
   * @deprecated {@code findTodos()} 메소드로 대체 사용
   */
  @Override
  public List<TodoResponseDto> findAllTodos() {
    return todoRepository.findAllTodos();
  }

  /**
   * 특정 조건에 맞는 일정을 가져오는 메소드
   *
   * @param dto 사용자 요청 객체
   * @return 조건에 해당하는 일정 정보 {@link TodoResponseDto} 객체 리스트
   */
  @Override
  public List<TodoResponseDto> findTodos(TodoRequestDto dto) {
    return todoRepository.findTodos(dto);
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
   * 식별자 id를 가진 일정을 수정하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @param dto         사용자 요청 객체
   * @return 수정된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   * @throws ResponseStatusException 비밀번호가 일치하지 않거나 요청 데이터가 없으면 BAD REQUEST, 수정된 데이터가 없으면 NOT FOUND
   */
  @Transactional
  @Override
  public TodoResponseDto updateTodo(Long schedule_id, TodoRequestDto dto) {
    if (todoRepository.isValidPwd(schedule_id, dto)) {
      int updatedRow = todoRepository.updateTodo(schedule_id, dto);
      if (updatedRow == 0) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
      }

      Todos todo = todoRepository.findTodoByIdOrElseThrow(schedule_id);

      return new TodoResponseDto(todo);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
    }
  }

  /**
   * 식별자 Id를 가진 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 저장된 일정 id
   * @param dto         사용자 요청 객체
   * @throws ResponseStatusException 삭제된 행이 없을 경우 NOT FOUND, 비밀번호가 일치하지 않을 경우 BAD REQUEST
   */
  @Override
  public void deleteMemo(Long schedule_id, TodoRequestDto dto) {
    if (todoRepository.isValidPwd(schedule_id, dto)) {
      int deletedRow = todoRepository.deleteTodo(schedule_id);
      if (deletedRow == 0) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "선택한 " + schedule_id + "아이디가 존재하지 않습니다.");
      }
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "비밀번호가 일치하지 않습니다.");
    }
  }

}
