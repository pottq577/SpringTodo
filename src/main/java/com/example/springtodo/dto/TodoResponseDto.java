package com.example.springtodo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

  private Long schedule_id;
  private String name;
  private String todo;
  private LocalDateTime created_date;
  private LocalDateTime updated_date;

  public TodoResponseDto(long schedule_id, String name, String todo, LocalDateTime created_date,
      LocalDateTime updated_date) {
    this.schedule_id = schedule_id;
    this.name = name;
    this.todo = todo;
    this.created_date = created_date;
    this.updated_date = updated_date;
  }

}
