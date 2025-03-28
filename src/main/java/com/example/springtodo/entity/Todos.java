package com.example.springtodo.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todos {

  private Long schedule_id;
  private String name;
  private String todo;
  private String password;
  private LocalDateTime created_date = LocalDateTime.now();
  private LocalDateTime updated_date;

  public Todos(String name, String todo, String password) {
    this.name = name;
    this.todo = todo;
    this.password = password;
  }

  public Todos(long scheduleId, String name, String todo, LocalDateTime createdDate,
      LocalDateTime updatedDate) {
    this.schedule_id = scheduleId;
    this.name = name;
    this.todo = todo;
    this.created_date = createdDate;
    this.updated_date = updatedDate;
  }
}
