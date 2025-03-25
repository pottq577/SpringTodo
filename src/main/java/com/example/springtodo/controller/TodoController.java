package com.example.springtodo.controller;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.service.TodoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

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
   * 모든 일정 정보를 요청하는 메소드
   *
   * @return 저장된 일정을 포함하는 객체
   */
  @GetMapping
  public List<TodoResponseDto> findAllTodos() {
    return todoService.findAllTodos();
  }

  /**
   * 식별자 Id를 사용해 일정 정보를 요청하는 메소드
   *
   * @param schedule_id URL에 지정된 사용자 id
   * @return 조회된 일정 정보와 응답 코드를 포함하는 응답 객체
   */
  @GetMapping("/{schedule_id}")
  public ResponseEntity<TodoResponseDto> findTodoById(@PathVariable Long schedule_id) {
    return new ResponseEntity<>(todoService.findTodoById(schedule_id), HttpStatus.OK);
  }

//  @GetMapping
//  public List<TodoResponseDto> findTodos(@RequestBody TodoResponseDto dto) {
//    return todoService.findTodos(dto.getUpdated_date(), dto.getName());
//  }


}