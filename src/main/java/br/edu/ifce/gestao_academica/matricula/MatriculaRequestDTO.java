package br.edu.ifce.gestao_academica.matricula;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatriculaRequestDTO {
    private Integer alunoId;
    private Integer turmaId;
    private StatusMatricula status;
}