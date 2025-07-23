package com.example.StudentAPI.adapters.http;

import com.example.StudentAPI.application.EstudanteCommand;
import com.example.StudentAPI.application.EstudanteService;
import com.example.StudentAPI.domain.Estudante;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EstudanteHandler {
    EstudanteService estudanteService;

    public EstudanteHandler(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    public ResponseEntity<List<Estudante>> findAll() {
        List<Estudante> estudantes = estudanteService.findAll();
        return ResponseEntity.ok(estudantes);
    }

    public ResponseEntity<Estudante> findById(UUID id) {
        Estudante estudante = estudanteService.findById(id);
        return ResponseEntity.ok(estudante);
    }

    public ResponseEntity<Estudante> inserir(EstudanteCommand estudanteCommand) {
        Estudante estudante = estudanteService.inserir(estudanteCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudante);
    }

    public ResponseEntity<Estudante> atualizar(EstudanteCommand estudanteCommand, UUID id) {
        Estudante estudante = estudanteService.atualizar(estudanteCommand, id);
        return ResponseEntity.ok(estudante);
    }

    public ResponseEntity<String> excluir(UUID id) {
        estudanteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
