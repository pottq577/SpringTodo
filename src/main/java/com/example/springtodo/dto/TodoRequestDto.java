package com.example.springtodo.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class TodoRequestDto {

  private String name;
  private String todo;
  private String password;
  private LocalDate updated_date;

}
