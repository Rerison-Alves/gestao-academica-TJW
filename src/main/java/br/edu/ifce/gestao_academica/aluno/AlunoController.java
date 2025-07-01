package br.edu.ifce.gestao_academica.aluno;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
@Tag(name = "Alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        Aluno salvo = alunoService.criar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Integer id, @RequestBody Aluno aluno) {
        try {
            Aluno atualizado = alunoService.atualizar(id, aluno);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}