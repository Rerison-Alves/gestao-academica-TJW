package br.edu.ifce.gestao_academica.matricula;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matriculas")
@Tag(name = "Matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @Operation(summary = "Save matricula", description = "Return saved matricula")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody MatriculaRequestDTO dto) {
        Matricula novaMatricula = matriculaService.matricularAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }

    @Operation(summary = "List of matriculas by Aluno", description = "Return list of matriculas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/aluno/{alunoId}")
    public List<Matricula> listarPorAluno(@PathVariable Integer alunoId) {
        return matriculaService.listarPorAluno(alunoId);
    }

    @Operation(summary = "List of matriculas by Turma", description = "Return list of matriculas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/turma/{turmaId}")
    public List<Matricula> listarPorTurma(@PathVariable Integer turmaId) {
        return matriculaService.listarPorTurma(turmaId);
    }

    @Operation(summary = "Matricula by id", description = "Return matricula")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Integer id) {
        return matriculaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update matricula", description = "Return updated matricula")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@PathVariable Integer id, @RequestBody MatriculaRequestDTO matriculaDto) {
        try {
            Matricula atualizada = matriculaService.atualizar(id, matriculaDto);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cancel matricula", description = "Return void")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        matriculaService.cancelarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}