package com.example.StudentAPI.controller;

import com.example.StudentAPI.model.Student;
import com.example.StudentAPI.repository.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class StudentController {

    private final StudentRepositoryImpl studentRepository;

    public StudentController(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable UUID id) {
        return studentRepository.getStudentById(id);
    }

    @PostMapping("/students")
    public int insertStudent(@RequestBody Student student) {
        return studentRepository.addStudent(student);
    }

    @PutMapping("/students/{id}")
    public int updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        return studentRepository.updateStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public int deleteStudent(@PathVariable UUID id) {
        return studentRepository.deleteStudent(id);
    }


}
