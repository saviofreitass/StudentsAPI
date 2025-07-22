package com.example.StudentAPI.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudanteCommand implements Serializable {
    private String nome;
    private String contato;
    private String endereco;
}
