package br.edu.ifce.gestao_academica.aluno;

import br.edu.ifce.gestao_academica.matricula.Matricula;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "Nome ${notblank}")
    @Size(min = 3, max = 100, message = "Nome ${size}")
    private String nome;

    @Column(unique = true)
    @NotBlank(message = "Email ${notblank}")
    private String email;

    private String telefone;

    @JsonFormat(pattern="dd/MM/yyyy")
    @NotNull(message = "Data de nascimento ${notblank}")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Matricula> matriculas = new ArrayList<>();
}

