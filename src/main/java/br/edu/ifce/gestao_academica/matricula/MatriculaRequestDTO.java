package br.edu.ifce.gestao_academica.matricula;

import lombok.Data;

@Data
public class MatriculaRequestDTO {
    private Integer alunoId;
    private Integer turmaId;
    private StatusMatricula status;
}