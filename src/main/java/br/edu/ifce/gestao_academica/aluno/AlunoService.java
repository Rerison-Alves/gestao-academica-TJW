package br.edu.ifce.gestao_academica.aluno;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Integer id, Aluno novoAluno) {
        Aluno existente = alunoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        existente.setNome(novoAluno.getNome());
        existente.setEmail(novoAluno.getEmail());
        existente.setTelefone(novoAluno.getTelefone());
        existente.setDataNascimento(novoAluno.getDataNascimento());

        return alunoRepository.save(existente);
    }

    public void deletar(Integer id) {
        alunoRepository.deleteById(id);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Integer id) {
        return alunoRepository.findById(id);
    }
}