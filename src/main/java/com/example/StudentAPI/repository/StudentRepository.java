package com.example.StudentAPI.repository;

import com.example.StudentAPI.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentRepository {

    public List<Student> findAll();
    public Student findById(UUID id);
    public boolean inserir(Student student);
    public boolean atualizar(Student student);
    public boolean excluir(UUID id);
}
