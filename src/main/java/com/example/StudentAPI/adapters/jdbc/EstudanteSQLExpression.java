package com.example.StudentAPI.adapters.jdbc;

public class EstudanteSQLExpression {
    public static final String CAMPOS = """
            id,
            nome,
            contato,
            endereco
            """;
    
    public static final String GET_ALL = "SELECT " + CAMPOS + " FROM estudantes";

    public static final String GET_BY_ID = "SELECT " + CAMPOS + " FROM estudantes WHERE id = :id";

    public static final String INSERT = """
            INSERT into estudantes(
                id,
                nome,
                contato,
                endereco)
            VALUES(
                :id,
                :nome,
                :contato,
                :endereco)""";

    public static final String UPDATE = """
            UPDATE estudantes
                SET nome = :nome,
                    contato = :contato,
                    endereco = :endereco
                WHERE id = :id""";

    public static final String DELETE = "DELETE FROM estudantes WHERE id = :id";

    private EstudanteSQLExpression() {}
}
