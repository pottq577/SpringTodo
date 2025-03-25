package com.example.springtodo.dto;

import com.example.springtodo.entity.Todos;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

  private Long schedule_id;
  private String name;
  private String password;
  private String todo;
  private LocalDateTime created_date;
  private LocalDateTime updated_date;

  public TodoResponseDto(Todos todos) {
    this.schedule_id = todos.getSchedule_id();
    this.name = todos.getName();
    this.password = todos.getPassword();
    this.todo = todos.getTodo();
    this.created_date = todos.getCreated_date();
    this.updated_date = todos.getUpdated_date();
  }

}
