package com.example.StudentAPI.adapters.http;

import com.example.StudentAPI.application.EstudanteCommand;
import com.example.StudentAPI.domain.Estudante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
public class EstudanteController {

    EstudanteHandler estudanteHandler;

    public EstudanteController(EstudanteHandler estudanteHandler) {
        this.estudanteHandler = estudanteHandler;
    }

    @GetMapping("/estudantes")
    public ResponseEntity<List<Estudante>> findAll() {
        return estudanteHandler.findAll();
    }

    @GetMapping("/estudantes/{id}")
    public ResponseEntity<Estudante> findById(@PathVariable UUID id) {
        return estudanteHandler.findById(id);
    }

    @PostMapping("/estudantes")
    public ResponseEntity<Estudante> inserir(@RequestBody EstudanteCommand estudante) {
        return estudanteHandler.inserir(estudante);
    }

    @PutMapping("/estudantes/{id}")
    public ResponseEntity<Estudante> atualizar(@RequestBody EstudanteCommand estudante, @PathVariable UUID id) {
        return estudanteHandler.atualizar(estudante, id);
    }

    @DeleteMapping("/estudantes/{id}")
    public ResponseEntity<String> excluir(@PathVariable UUID id) {
        return estudanteHandler.excluir(id);
    }
}
