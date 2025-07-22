package com.example.StudentAPI.domain;

import java.util.List;
import java.util.UUID;

public interface EstudanteRepository {

    public List<Estudante> findAll();
    public Estudante findById(UUID id);
    public boolean inserir(Estudante estudante);
    public boolean atualizar(Estudante estudante);
    public boolean deletar(UUID id);
}
