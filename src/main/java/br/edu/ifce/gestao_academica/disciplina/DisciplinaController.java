package br.edu.ifce.gestao_academica.disciplina;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplinas")
@Tag(name = "Disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Operation(summary = "Save disciplina", description = "Return saved disciplina")
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<Disciplina> criar(@RequestBody Disciplina disciplina) {
        Disciplina salva = disciplinaService.criar(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @Operation(summary = "List of disciplinas", description = "Return list of disciplinas")
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<Disciplina> listarTodos() {
        return disciplinaService.listarTodos();
    }

    @Operation(summary = "Disciplina by id", description = "Return disciplina")
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Integer id) {
        return disciplinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update disciplina", description = "Return updated disciplina")
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Integer id, @RequestBody Disciplina disciplina) {
        try {
            Disciplina atualizada = disciplinaService.atualizar(id, disciplina);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete disciplina", description = "Return void")
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        disciplinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}