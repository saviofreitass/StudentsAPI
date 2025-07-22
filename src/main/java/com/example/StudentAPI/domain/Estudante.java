package com.example.StudentAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudante {
    private UUID id;
    private String nome;
    private String contato;
    private String endereco;
}