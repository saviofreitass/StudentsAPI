package com.example.StudentAPI.repository;

public class StudentsSqlExpressions {
    public static final String CAMPOS = """
            id,
            name,
            contact_number,
            address
            """;
    public static final String INSERT = """
            INSERT into student(
                id,
                name,
                contact_number,
                address)
            VALUES(:id, :name, :contact_number, :address)""".trim();

    public static final String GET_ALL = "SELECT " + CAMPOS + " FROM student";

    public static final String GET_BY_ID = "SELECT " + CAMPOS + " FROM student WHERE id = :id";

    public static final String UPDATE = """
            UPDATE student
                SET name = :name,
                    contact_number = :contact_number,
                    address = :address
                    WHERE id = :id""";

    public static final String DELETE = "DELETE FROM student WHERE id = :id";

    private StudentsSqlExpressions() {}
}
