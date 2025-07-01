package br.edu.ifce.gestao_academica.matricula;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    List<Matricula> findByAluno_Id(Integer alunoId);

    List<Matricula> findByTurma_Id(Integer turmaId);
}