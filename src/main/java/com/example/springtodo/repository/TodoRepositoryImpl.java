package com.example.springtodo.repository;

import com.example.springtodo.dto.TodoRequestDto;
import com.example.springtodo.dto.TodoResponseDto;
import com.example.springtodo.entity.Todos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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
    parameters.put("todo", todos.getTodo());
    parameters.put("password", todos.getPassword());
    parameters.put("created_date", Timestamp.valueOf(LocalDateTime.now()));
    parameters.put("updated_date", Timestamp.valueOf(LocalDateTime.now()));
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

    return new TodoResponseDto(
        key.longValue(),
        todos.getName(),
        todos.getTodo(),
        LocalDateTime.now(),
        LocalDateTime.now());

  }

  /**
   * DB에서 수정일을 기준으로 내림차순으로 정렬된 일정 정보를 모두 가져와 반환하는 메소드
   *
   * @return schedule 테이블에 존재하는 모든 {@link TodoResponseDto} 객체의 리스트
   */
  @Override
  public List<TodoResponseDto> findAllTodos() {
    return jdbcTemplate.query(
        "SELECT * FROM schedule ORDER BY updated_date DESC",
        todoDtoRowMapper());
  }

  /**
   * 식별자 Id를 가진 일정 정보를 반환하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return schedule 테이블에서 조회된 결과와 응답 코드를 포함하는 {@link Todos} 객체
   */
  @Override
  public Todos findTodoByIdOrElseThrow(Long schedule_id) {
    List<Todos> result = jdbcTemplate.query(
        "SELECT * FROM schedule WHERE schedule_id = ?",
        todoRowMapper(), schedule_id);

    return result.stream()
        .findAny()
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Does not exist id = " + schedule_id)
        );
  }

  /**
   * 식별자 id를 가진 일정을 삭제하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @return 쿼리의 결과로 반환된 행의 개수
   */
  @Override
  public int deleteTodo(Long schedule_id) {
    return jdbcTemplate.update("DELETE FROM schedule WHERE schedule_id = ?", schedule_id);
  }

  /**
   * 선택 일정의 비밀번호와 사용자가 입력한 비밀번호랑 일치하는지 확인하는 메소드
   *
   * @param schedule_id URL에 지정된 일정 id
   * @param dto         사용자 요청 객체
   * @return 비밀번호가 일치하면 true
   */
  @Override
  public boolean isValidPwd(Long schedule_id, TodoRequestDto dto) {
    Todos todos = findTodoByIdOrElseThrow(schedule_id);
    return todos.getPassword().equals(dto.getPassword());
  }

  private RowMapper<TodoResponseDto> todoDtoRowMapper() {
    return new RowMapper<TodoResponseDto>() {
      @Override
      public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TodoResponseDto(
            rs.getLong("schedule_id"),
            rs.getString("name"),
            rs.getString("todo"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
        );
      }
    };

  }

  private RowMapper<Todos> todoRowMapper() {
    return new RowMapper<Todos>() {
      @Override
      public Todos mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Todos(
            rs.getLong("schedule_id"),
            rs.getString("name"),
            rs.getString("todo"),
            rs.getString("password"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
        );
      }
    };
  }

  // 특정 조건을 만족하는 일정 데이터를 모두 가져와 반환하는 메소드
//  @Override
//  public List<TodoResponseDto> findTodos(LocalDateTime updated_date, String name) {
//    String query = "SELECT * FROM schedule WHERE";
//    PreparedStatement pstmt;
//
//    if (updated_date != null || name != null) {
//      if (updated_date != null) {
//      }
//      if (name != null) {
//      }
//    }
//
//    return jdbcTemplate.query(query, todoRowMapper());
//
//  }

}
