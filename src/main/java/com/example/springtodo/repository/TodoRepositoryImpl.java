package com.example.springtodo.repository;

import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 * 일정 데이터 관리 Repository 구현 클래스 {@link TodoRepository} 구현
 */
@Repository
public class TodoRepositoryImpl implements TodoRepository {

  private final JdbcTemplate jdbcTemplate;

  public TodoRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * 일정 정보를 DB에 저장 후, 일정의 ID (schedule_id)를 반환하는 메소드
   *
   * @param todos 저장할 일정 정보를 담고 있는 {@link Todos} 객체
   * @return 생성된 일정 정보를 포함하는 {@link TodoResponseDto} 객체
   */
  @Override
  public TodoResponseDto saveTodo(Todos todos) {

    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id");

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", todos.getName());
    parameters.put("password", todos.getPassword());
    parameters.put("todo", todos.getTodo());

    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    return new TodoResponseDto(
        key.longValue(),
        todos.getName(),
        todos.getTodo(),
        LocalDateTime.now().toString(),
        LocalDateTime.now().toString());

  }

  @Override
  public List<TodoResponseDto> findAllTodos() {
    return jdbcTemplate.query("SELECT * FROM schedule", memoRowMapper());
  }

  private RowMapper<TodoResponseDto> memoRowMapper() {
    return new RowMapper<TodoResponseDto>() {
      @Override
      public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TodoResponseDto(
            rs.getLong("schedule_id"),
            rs.getString("name"),
            rs.getString("todo")
        );
      }
    };

  }

}
