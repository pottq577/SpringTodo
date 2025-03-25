package com.example.springtodo.controller;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.service.TodoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
   * @return 일정이 생성된 후, 생성된 일정 정보를 포함하는 응답 객체
   */
  @PostMapping
  public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto dto) {
    return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
  }

  @GetMapping
  public List<TodoResponseDto> findAllTodos() {
    return todoService.findAllTodos();
  }

}