package com.example.springtodo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todos {

  private Long id;
  private String name;
  private String todo;
  private String password;
  private String created_date;
  private String updated_date;

}
