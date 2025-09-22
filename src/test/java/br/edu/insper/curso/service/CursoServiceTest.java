package br.edu.insper.curso.service;

import br.edu.insper.curso.model.Curso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CursoServiceTest {

    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        cursoService = new CursoService();
    }

    @Test
    void deveCadastrarCurso() {
        Curso curso = new Curso();
        curso.setTitulo("Java Básico");
        curso.setDescricao("Curso de introdução ao Java");
        curso.setCargaHoraria(40);
        curso.setInstrutor("Maria");

        Curso salvo = cursoService.cadastrarCurso(curso);

        assertNotNull(salvo.getId());
        assertEquals("Java Básico", salvo.getTitulo());
        assertNotNull(salvo.getDataCadastro());
    }

    @Test
    void deveListarCursos() {
        cursoService.cadastrarCurso(new Curso());
        List<Curso> cursos = cursoService.listarCursos();

        assertFalse(cursos.isEmpty());
    }

    @Test
    void deveExcluirCursoExistente() {
        Curso curso = new Curso();
        curso.setTitulo("Spring Boot");
        Curso salvo = cursoService.cadastrarCurso(curso);

        boolean removido = cursoService.excluirCurso(salvo.getId());

        assertTrue(removido);
        assertTrue(cursoService.listarCursos().isEmpty());
    }

    @Test
    void naoDeveExcluirCursoInexistente() {
        boolean removido = cursoService.excluirCurso(999L);
        assertFalse(removido);
    }
}
