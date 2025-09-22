package br.edu.insper.curso.controller;

import br.edu.insper.curso.model.Curso;
import br.edu.insper.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @PostMapping
    public Curso cadastrarCurso(@RequestBody Curso curso) {
        return cursoService.cadastrarCurso(curso);
    }

    @DeleteMapping("/{id}")
    public String excluirCurso(@PathVariable Long id) {
        boolean removido = cursoService.excluirCurso(id);
        if (removido) {
            return "Curso com ID " + id + " removido com sucesso.";
        } else {
            return "Curso com ID " + id + " não encontrado.";
        }
    }
}
