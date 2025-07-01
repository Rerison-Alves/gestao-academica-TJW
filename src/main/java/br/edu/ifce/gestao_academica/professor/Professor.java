package br.edu.ifce.gestao_academica.professor;

import br.edu.ifce.gestao_academica.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String telefone;
    private String areaAtuacao;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas = new ArrayList<>();
}