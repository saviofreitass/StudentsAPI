package com.example.StudentAPI.application.exceptions;

import java.util.UUID;

public class EstudanteExceptions extends RuntimeException {
    public EstudanteExceptions(UUID id) {
        super("Estudante id: " + id + " não foi encontrado");
    }
}
