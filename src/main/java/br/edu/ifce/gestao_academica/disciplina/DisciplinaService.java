package br.edu.ifce.gestao_academica.disciplina;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public Disciplina criar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizar(Integer id, Disciplina novaDisciplina) {
        Disciplina existente = disciplinaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        existente.setNome(novaDisciplina.getNome());
        existente.setCodigo(novaDisciplina.getCodigo());
        existente.setEmenta(novaDisciplina.getEmenta());
        existente.setCargaHoraria(novaDisciplina.getCargaHoraria());

        return disciplinaRepository.save(existente);
    }

    public void deletar(Integer id) {
        disciplinaRepository.deleteById(id);
    }

    public List<Disciplina> listarTodos() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarPorId(Integer id) {
        return disciplinaRepository.findById(id);
    }
}
