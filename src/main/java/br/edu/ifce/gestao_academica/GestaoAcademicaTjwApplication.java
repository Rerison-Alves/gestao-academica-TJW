package br.edu.ifce.gestao_academica;

import br.edu.ifce.gestao_academica.aluno.Aluno;
import br.edu.ifce.gestao_academica.aluno.AlunoService;
import br.edu.ifce.gestao_academica.disciplina.Disciplina;
import br.edu.ifce.gestao_academica.disciplina.DisciplinaService;
import br.edu.ifce.gestao_academica.matricula.MatriculaRequestDTO;
import br.edu.ifce.gestao_academica.matricula.MatriculaService;
import br.edu.ifce.gestao_academica.matricula.StatusMatricula;
import br.edu.ifce.gestao_academica.professor.Professor;
import br.edu.ifce.gestao_academica.professor.ProfessorService;
import br.edu.ifce.gestao_academica.turma.Turma;
import br.edu.ifce.gestao_academica.turma.TurmaRequestDTO;
import br.edu.ifce.gestao_academica.turma.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableScheduling
public class GestaoAcademicaTjwApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestaoAcademicaTjwApplication.class, args);
	}

	private final AlunoService alunoService;
	private final ProfessorService professorService;
	private final DisciplinaService disciplinaService;
	private final TurmaService turmaService;
	private final MatriculaService matriculaService;

	@Override
	public void run(String... args) {

//		# Povoando o banco

		Aluno aluno1 = new Aluno(null, "Ana Souza", "ana@ifce.edu.br", "8599991001", LocalDate.of(2002, 1, 10), new ArrayList<>());
		Aluno aluno2 = new Aluno(null, "Bruno Lima", "bruno@ifce.edu.br", "8599991002", LocalDate.of(2001, 5, 22), new ArrayList<>());
		Aluno aluno3 = new Aluno(null, "Clara Dias", "clara@ifce.edu.br", "8599991003", LocalDate.of(2003, 3, 15), new ArrayList<>());
		aluno1 = alunoService.criar(aluno1);
		aluno2 = alunoService.criar(aluno2);
		aluno3 = alunoService.criar(aluno3);

		Professor prof1 = new Professor(null, "Carlos Mendes", "carlos@ifce.edu.br", "8599992001", "Matemática", new ArrayList<>());
		Professor prof2 = new Professor(null, "Daniela Rocha", "daniela@ifce.edu.br", "8599992002", "Programação", new ArrayList<>());
		Professor prof3 = new Professor(null, "Eduardo Torres", "eduardo@ifce.edu.br", "8599992003", "Redes", new ArrayList<>());
		prof1 = professorService.criar(prof1);
		prof2 = professorService.criar(prof2);
		prof3 = professorService.criar(prof3);

		Disciplina disc1 = new Disciplina(null, "Cálculo I", "MAT101", "Limites, derivadas", 60, new ArrayList<>());
		Disciplina disc2 = new Disciplina(null, "POO", "INF201", "Programação Orientada a Objetos", 80, new ArrayList<>());
		Disciplina disc3 = new Disciplina(null, "Redes I", "INF301", "Camadas OSI, IP, TCP", 60, new ArrayList<>());
		disc1 = disciplinaService.criar(disc1);
		disc2 = disciplinaService.criar(disc2);
		disc3 = disciplinaService.criar(disc3);

		TurmaRequestDTO turma1DTO = new TurmaRequestDTO("TURMA-A", "Manhã", "2025.1", prof1.getId(), disc1.getId());
		TurmaRequestDTO turma2DTO = new TurmaRequestDTO("TURMA-B", "Tarde", "2025.1", prof2.getId(), disc2.getId());
		TurmaRequestDTO turma3DTO = new TurmaRequestDTO("TURMA-C", "Noite", "2025.1", prof3.getId(), disc3.getId());

		Turma turma1 = turmaService.criar(turma1DTO);
		Turma turma2 = turmaService.criar(turma2DTO);
		Turma turma3 = turmaService.criar(turma3DTO);

		matriculaService.matricularAluno(new MatriculaRequestDTO(aluno1.getId(), turma1.getId(), StatusMatricula.ATIVA));
		matriculaService.matricularAluno(new MatriculaRequestDTO(aluno2.getId(), turma2.getId(), StatusMatricula.ATIVA));
		matriculaService.matricularAluno(new MatriculaRequestDTO(aluno3.getId(), turma3.getId(), StatusMatricula.ATIVA));
		matriculaService.matricularAluno(new MatriculaRequestDTO(aluno1.getId(), turma2.getId(), StatusMatricula.ATIVA));
		matriculaService.matricularAluno(new MatriculaRequestDTO(aluno2.getId(), turma3.getId(), StatusMatricula.ATIVA));
		matriculaService.matricularAluno(new MatriculaRequestDTO(aluno3.getId(), turma1.getId(), StatusMatricula.ATIVA));

	}

}
