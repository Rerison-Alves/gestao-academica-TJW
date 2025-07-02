package br.edu.ifce.gestao_academica.matricula;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "Matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody MatriculaRequestDTO dto) {
        Matricula novaMatricula = matriculaService.matricularAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/aluno/{alunoId}")
    public List<Matricula> listarPorAluno(@PathVariable Integer alunoId) {
        return matriculaService.listarPorAluno(alunoId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/turma/{turmaId}")
    public List<Matricula> listarPorTurma(@PathVariable Integer turmaId) {
        return matriculaService.listarPorTurma(turmaId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        matriculaService.cancelarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}