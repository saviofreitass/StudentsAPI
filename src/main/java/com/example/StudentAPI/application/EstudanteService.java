package com.example.StudentAPI.application;

import com.example.StudentAPI.domain.Estudante;
import com.example.StudentAPI.domain.EstudanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstudanteService {

    EstudanteRepository estudanteRepository;

    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    public List<Estudante> findAll(){
        return estudanteRepository.findAll();
    }

    public Estudante findById(UUID id){
        return estudanteRepository.findById(id);
    }

    public Estudante inserir(EstudanteCommand estudante){
        Estudante estudanteDomain = EstudanteMapper.toEstudante(estudante);
        estudanteRepository.inserir(estudanteDomain);
        return estudanteRepository.findById(estudanteDomain.getId());
    }

    public Estudante atualizar(EstudanteCommand estudante, UUID id){
        estudanteRepository.atualizar(EstudanteMapper.toEstudante(estudante));
        return estudanteRepository.findById(id);
    }

    public void excluir(UUID id){
        estudanteRepository.deletar(id);
    }
}
