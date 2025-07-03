package br.edu.ifce.gestao_academica.disciplina;

import br.edu.ifce.gestao_academica.turma.Turma;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Column(unique = true)
    @NotBlank(message = "Nome da disciplina ${notblank}")
    @Size(min = 3, max = 100, message = "Nome da disciplina ${size}")
    private String nome;

    private String codigo;

    private String ementa;

    @NotNull(message = "Carga horária ${notblank}")
    private Integer cargaHoraria;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnore
    private List<Turma> turmas = new ArrayList<>();
}