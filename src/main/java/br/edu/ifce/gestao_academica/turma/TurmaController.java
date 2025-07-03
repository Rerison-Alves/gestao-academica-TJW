package br.edu.ifce.gestao_academica.turma;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turmas")
@Tag(name = "Turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @Operation(summary = "Save turma", description = "Return saved turma")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody TurmaRequestDTO dto) {
        Turma salva = turmaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @Operation(summary = "List of turmas", description = "Return list of turmas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Turma> listarTodos() {
        return turmaService.listarTodos();
    }

    @Operation(summary = "Turma by id", description = "Return turma")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Integer id) {
        return turmaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update turma", description = "Return updated turma")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Integer id, @RequestBody TurmaRequestDTO turmaDto) {
        try {
            Turma atualizada = turmaService.atualizar(id, turmaDto);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete turma", description = "Return void")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}