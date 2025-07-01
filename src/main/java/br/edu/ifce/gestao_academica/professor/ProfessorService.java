package br.edu.ifce.gestao_academica.professor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public Professor criar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizar(Integer id, Professor novoProfessor) {
        Professor existente = professorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        existente.setNome(novoProfessor.getNome());
        existente.setEmail(novoProfessor.getEmail());
        existente.setTelefone(novoProfessor.getTelefone());
        existente.setAreaAtuacao(novoProfessor.getAreaAtuacao());

        return professorRepository.save(existente);
    }

    public void deletar(Integer id) {
        professorRepository.deleteById(id);
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Integer id) {
        return professorRepository.findById(id);
    }
}