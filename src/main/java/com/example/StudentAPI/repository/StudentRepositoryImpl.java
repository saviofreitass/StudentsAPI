package com.example.StudentAPI.repository;

import com.example.StudentAPI.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final NamedParameterJdbcTemplate db;

    public StudentRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.db = namedParameterJdbcTemplate;
    }

    @Override
    public int addStudent(Student student) {
        return db.update(StudentsSqlExpressions.INSERT, parametros(student));
    }

    @Override
    public List<Student> getAllStudents() {
        return db.query(StudentsSqlExpressions.GET_ALL, rowMapper);
    }

    @Override
    public Student getStudentById(UUID id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return db.queryForObject(StudentsSqlExpressions.GET_BY_ID,params, rowMapper);
    }

    @Override
    public int updateStudent(Student student) {
        return db.update(StudentsSqlExpressions.UPDATE, parametros(student));
    }

    @Override
    public int deleteStudent(UUID id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return db.update(StudentsSqlExpressions.DELETE, params);
    }

    RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            UUID.fromString(rs.getString("id")),
            rs.getString("name"),
            rs.getString("contact_number"),
            rs.getString("address")
    );

    private MapSqlParameterSource parametros(Student student) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", student.getId());
        map.addValue("name", student.getName());
        map.addValue("contact_number", student.getContactNumber());
        map.addValue("address", student.getAddress());
        return map;
    }
}
