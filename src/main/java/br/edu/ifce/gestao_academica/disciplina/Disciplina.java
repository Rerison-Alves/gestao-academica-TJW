package br.edu.ifce.gestao_academica.disciplina;

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
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String codigo;
    private String ementa;
    private Integer cargaHoraria;

    @OneToMany(mappedBy = "disciplina")
    private List<Turma> turmas = new ArrayList<>();
}