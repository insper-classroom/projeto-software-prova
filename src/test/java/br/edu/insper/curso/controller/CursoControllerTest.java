package br.edu.insper.curso.controller;

import br.edu.insper.curso.model.Curso;
import br.edu.insper.curso.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarCursos() throws Exception {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setTitulo("Java");
        curso.setDataCadastro(LocalDate.now());

        when(cursoService.listarCursos()).thenReturn(Collections.singletonList(curso));

        mockMvc.perform(get("/api/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Java"));
    }

    @Test
    void deveCadastrarCurso() throws Exception {
        Curso curso = new Curso();
        curso.setTitulo("Spring Boot");
        curso.setDescricao("APIs com Spring");
        curso.setInstrutor("João");
        curso.setCargaHoraria(20);

        Curso salvo = new Curso();
        salvo.setId(1L);
        salvo.setTitulo("Spring Boot");
        salvo.setDescricao("APIs com Spring");
        salvo.setInstrutor("João");
        salvo.setCargaHoraria(20);
        salvo.setDataCadastro(LocalDate.now());

        when(cursoService.cadastrarCurso(any(Curso.class))).thenReturn(salvo);

        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Spring Boot"));
    }

    @Test
    void deveExcluirCurso() throws Exception {
        when(cursoService.excluirCurso(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Curso com ID 1 removido com sucesso."));
    }

    @Test
    void naoDeveExcluirCursoInexistente() throws Exception {
        when(cursoService.excluirCurso(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/cursos/99"))
                .andExpect(status().isOk())
                .andExpect(content().string("Curso com ID 99 não encontrado."));
    }
}
