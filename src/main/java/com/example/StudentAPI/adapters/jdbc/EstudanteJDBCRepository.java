package com.example.StudentAPI.adapters.jdbc;

import com.example.StudentAPI.application.exceptions.EstudanteExceptions;
import com.example.StudentAPI.domain.Estudante;
import com.example.StudentAPI.domain.EstudanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EstudanteJDBCRepository implements EstudanteRepository {

    NamedParameterJdbcTemplate db;
    Logger logger = LoggerFactory.getLogger(EstudanteJDBCRepository.class);

    public EstudanteJDBCRepository(NamedParameterJdbcTemplate db) {
        this.db = db;
    }

    @Override
    public List<Estudante> findAll() {
        List<Estudante> estudantes;
        try {
            estudantes = db.query(EstudanteSQLExpression.GET_ALL,rowMapper);
        }catch (Exception ex) {
            logger.error("Erro ao buscar estudantes, {}", ex.getMessage());
            throw ex;
        }
        return estudantes;
    }

    @Override
    public Estudante findById(UUID id) {
        Estudante estudante;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("id", id);
            estudante = db.queryForObject(EstudanteSQLExpression.GET_BY_ID, params, rowMapper);
            if(estudante == null){
                throw new EstudanteExceptions(id);
            }
        }catch (Exception ex) {
            logger.error("Erro ao buscar estudante, {}", ex.getMessage());
            throw ex;
        }
        return estudante;
    }

    @Override
    public boolean inserir(Estudante estudante) {
        try{
            int linhasAfetadas = db.update(EstudanteSQLExpression.INSERT, params(estudante));
            return linhasAfetadas > 0;
        }catch (Exception ex) {
            logger.error("Erro ao inserir estudante, {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public boolean atualizar(UUID id, Estudante estudante) {
        try{
            int linhasAfetadas = db.update(EstudanteSQLExpression.UPDATE, params(id, estudante));
            return linhasAfetadas > 0;
        }catch (Exception ex) {
            logger.error("Erro ao atualizar estudante, {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public boolean deletar(UUID id) {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
            int linhasAfetadas = db.update(EstudanteSQLExpression.DELETE, params);
            return linhasAfetadas == 1;
        }catch (Exception ex) {
            logger.error("Erro ao deletar estudante, {}", ex.getMessage());
            throw ex;
        }
    }

    RowMapper<Estudante> rowMapper = (rs, rowNum) ->
        new Estudante(
                UUID.fromString(rs.getString("id")),
                rs.getString("nome"),
                rs.getString("contato"),
                rs.getString("endereco")
        );

    private MapSqlParameterSource params(Estudante estudante) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", estudante.getId());
        params.addValue("nome", estudante.getNome());
        params.addValue("contato", estudante.getContato());
        params.addValue("endereco", estudante.getEndereco());
        return params;
    }

    private MapSqlParameterSource params(UUID id, Estudante estudanteAtualizado) {
        Estudante estudanteAtual = findById(id);
        String nome = estudanteAtualizado.getNome() != null ? estudanteAtualizado.getNome() : estudanteAtual.getNome();
        String contato = estudanteAtualizado.getContato() != null ? estudanteAtualizado.getContato() : estudanteAtual.getContato();
        String endereco = estudanteAtualizado.getEndereco() != null ? estudanteAtualizado.getEndereco() : estudanteAtual.getEndereco();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("nome", nome);
        params.addValue("contato", contato);
        params.addValue("endereco", endereco);
        return params;
    }
}
