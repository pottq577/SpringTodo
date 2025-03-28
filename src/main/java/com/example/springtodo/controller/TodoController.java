package com.example.springtodo.controller;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  /**
   * 일정 생성 요청을 처리하는 메소드
   *
   * @param dto 요청 본문에 포함된 일정 정보 (이름, 할 일, 비밀번호)
   * @return 일정이 생성된 후, 생성된 일정 정보와 응답 코드를 포함하는 응답 객체
   */
  @PostMapping
  public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto dto) {
    return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
  }

  /**
   * @return 저장된 일정을 포함하는 객체 리스트
   * @see #findTodos(TodoRequestDto)
   * @deprecated {@code findTodos()} 메소드로 대체 사용
   * <p>
   * {@link #findTodos(TodoRequestDto)} 를 사용하여 모든 일정뿐만 아니라 특정 조건을 만족하는 일정 정보를 가져옵니다.
   * </p>
   */
  @GetMapping("/api/old")
  public List<TodoResponseDto> findAllTodos() {
    return todoService.findAllTodos();
  }

  /**
   * 특정 조건을 만족하는 일정을 가져오도록 요청하는 메소드
   *
   * @param dto 사용자 요청 {@link TodoRequestDto} 객체
   * @return 조건에 해당하는 일정 정보 {@link TodoResponseDto} 객체 리스트
   */
  @GetMapping
  public List<TodoResponseDto> findTodos(@RequestBody TodoRequestDto dto) {
    return todoService.findTodos(dto);
  }

  /**
   * 식별자 Id를 사용해 일정 정보를 요청하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return 조회된 일정 정보와 응답 코드를 포함하는 응답 객체
   */
  @GetMapping("/{schedule_id}")
  public ResponseEntity<TodoResponseDto> findTodoById(@PathVariable Long schedule_id) {
    return new ResponseEntity<>(todoService.findTodoById(schedule_id), HttpStatus.OK);
  }

  /**
   * 식별자 id를 사용해 일정 정보 수정을 요청하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @param dto         사용자 요청 객체
   * @return 수정된 일정 정보와 응답 코드를 포함하는 응답 객체
   */
  @PatchMapping("/{schedule_id}")
  public ResponseEntity<TodoResponseDto> updateTodo(
      @PathVariable Long schedule_id,
      @RequestBody TodoRequestDto dto
  ) {
    return new ResponseEntity<>(todoService.updateTodo(schedule_id, dto), HttpStatus.OK);
  }

  /**
   * 식별자 id를 사용해 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 Id
   * @param dto         사용자 요청 객체
   * @return 응답 코드 객체
   */
  @DeleteMapping("/{schedule_id}")
  public ResponseEntity<Void> deleteTodo(
      @PathVariable Long schedule_id,
      @RequestBody TodoRequestDto dto
  ) {
    todoService.deleteMemo(schedule_id, dto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}