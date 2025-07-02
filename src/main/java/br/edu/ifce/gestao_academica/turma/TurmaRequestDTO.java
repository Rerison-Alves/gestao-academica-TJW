package br.edu.ifce.gestao_academica.turma;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TurmaRequestDTO {
    private String codigo;
    private String periodo;
    private String semestre;
    private Integer professorId;
    private Integer disciplinaId;
}