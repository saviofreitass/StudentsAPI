package com.example.StudentAPI.application;

import com.example.StudentAPI.domain.Estudante;
import java.util.UUID;

public class EstudanteMapper {

    public static Estudante toEstudante(EstudanteCommand estudante){
        UUID id = UUID.randomUUID();
        return new Estudante(
                id,
                estudante.getNome(),
                estudante.getContato(),
                estudante.getEndereco()
        );
    }
}
