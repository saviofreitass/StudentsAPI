package com.example.StudentAPI.repository;

import com.example.StudentAPI.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentRepository {

    public int addStudent(Student student);
    public List<Student> getAllStudents();
    public Student getStudentById(UUID id);
    public int updateStudent(Student student);
    public int deleteStudent(UUID id);
}
