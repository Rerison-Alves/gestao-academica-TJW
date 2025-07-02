package br.edu.ifce.gestao_academica.matricula;

import br.edu.ifce.gestao_academica.aluno.Aluno;
import br.edu.ifce.gestao_academica.aluno.AlunoRepository;
import br.edu.ifce.gestao_academica.turma.Turma;
import br.edu.ifce.gestao_academica.turma.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public Matricula matricularAluno(MatriculaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setDataMatricula(LocalDate.now());
        matricula.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusMatricula.ATIVA);

        return matriculaRepository.save(matricula);
    }

    public List<Matricula> listarPorAluno(Integer alunoId) {
        return matriculaRepository.findByAluno_Id(alunoId);
    }

    public List<Matricula> listarPorTurma(Integer turmaId) {
        return matriculaRepository.findByTurma_Id(turmaId);
    }

    public Optional<Matricula> buscarPorId(Integer id) {
        return matriculaRepository.findById(id);
    }

    public void cancelarMatricula(Integer id) {
        Matricula m = matriculaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        m.setStatus(StatusMatricula.CANCELADA);
        matriculaRepository.save(m);
    }
}