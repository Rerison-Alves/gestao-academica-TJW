package br.edu.ifce.gestao_academica.turma;

import br.edu.ifce.gestao_academica.disciplina.Disciplina;
import br.edu.ifce.gestao_academica.disciplina.DisciplinaRepository;
import br.edu.ifce.gestao_academica.professor.Professor;
import br.edu.ifce.gestao_academica.professor.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public Turma criar(TurmaRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Turma turma = new Turma();
        turma.setCodigo(dto.getCodigo());
        turma.setPeriodo(dto.getPeriodo());
        turma.setSemestre(dto.getSemestre());
        turma.setProfessor(professor);
        turma.setDisciplina(disciplina);

        return turmaRepository.save(turma);
    }

    public Turma atualizar(Integer id, TurmaRequestDTO novaTurma) {
        Turma existente = turmaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        existente.setCodigo(novaTurma.getCodigo());
        existente.setPeriodo(novaTurma.getPeriodo());
        existente.setSemestre(novaTurma.getSemestre());

        if (novaTurma.getDisciplinaId() != null) {
            Disciplina novaDisciplina = disciplinaRepository.findById(novaTurma.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            existente.setDisciplina(novaDisciplina);
        }

        if (novaTurma.getProfessorId()!= null) {
            Professor novoProfessor = professorRepository.findById(novaTurma.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            existente.setProfessor(novoProfessor);
        }

        return turmaRepository.save(existente);
    }

    public void deletar(Integer id) {
        turmaRepository.deleteById(id);
    }

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorId(Integer id) {
        return turmaRepository.findById(id);
    }
}