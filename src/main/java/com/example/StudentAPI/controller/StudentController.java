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
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable UUID id) {
        return studentRepository.findById(id);
    }

    @PostMapping("/students")
    public boolean insertStudent(@RequestBody Student student) {
        return studentRepository.inserir(student);
    }

    @PutMapping("/students/{id}")
    public boolean updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        return studentRepository.atualizar(student);
    }

    @DeleteMapping("/students/{id}")
    public boolean deleteStudent(@PathVariable UUID id) {
        return studentRepository.excluir(id);
    }


}
