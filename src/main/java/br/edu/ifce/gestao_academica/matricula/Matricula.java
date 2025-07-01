package br.edu.ifce.gestao_academica.matricula;

import br.edu.ifce.gestao_academica.aluno.Aluno;
import br.edu.ifce.gestao_academica.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    private StatusMatricula status;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
}