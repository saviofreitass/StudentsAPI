package com.example.StudentAPI.repository;

import com.example.StudentAPI.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final NamedParameterJdbcTemplate db;
    private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    public StudentRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.db = namedParameterJdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        List<Student> estudantes;
                try{
                   estudantes = db.query(StudentsSqlExpressions.GET_ALL, rowMapper);
                }catch(Exception ex){
                    logger.error("Houve um erro ao consultar estudantes. {}", ex.getMessage());
                    throw ex;
                }
        return estudantes;
    }

    @Override
    public Student findById(UUID id) {
        Student estudante;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource()
                                            .addValue("id", id);
            estudante = db.queryForObject(StudentsSqlExpressions.GET_BY_ID,params, rowMapper);
        }catch (Exception ex){
            logger.error("Houve um erro ao consultar estudante. {}", ex.getMessage());
            throw ex;
        }
        return estudante;
    }

    @Override
    public boolean inserir(Student student) {
        try {
            int linhasAfetadas = db.update(StudentsSqlExpressions.INSERT, parametros(student));
            return linhasAfetadas > 0;
        }catch (Exception ex){
            logger.error("Houve um erro ao inserir estudante. {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public boolean atualizar(Student student) {
        try{
            int linhasAfetadas = db.update(StudentsSqlExpressions.UPDATE, parametros(student));
            return linhasAfetadas > 0;
        }catch (Exception ex){
            logger.error("Houve um erro ao atualizar estudante. {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public boolean excluir(UUID id) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("id", id);
            int linhasAfetadas = db.update(StudentsSqlExpressions.DELETE, params);
            ;
            return linhasAfetadas == 0;
        }catch (Exception ex){
            logger.error("Houve um erro ao excluir estudante. {}", ex.getMessage());
            throw ex;
        }
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
