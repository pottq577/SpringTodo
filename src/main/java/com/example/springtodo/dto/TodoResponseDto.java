package com.example.springtodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

  private Long schedule_id;
  private String name;
  private String todo;
  private String created_date;
  private String updated_date;

}
