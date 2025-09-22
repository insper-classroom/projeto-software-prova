package br.edu.insper.curso.service;

import br.edu.insper.curso.model.Curso;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CursoService {

    private List<Curso> cursos = new ArrayList<>();
    private Long proximoId = 1L;

    public List<Curso> listarCursos() {
        return cursos;
    }

    public Curso cadastrarCurso(Curso curso) {
        curso.setId(proximoId++);
        curso.setDataCadastro(LocalDate.now());
        cursos.add(curso);
        return curso;
    }

    public boolean excluirCurso(Long id) {
        Iterator<Curso> iterator = cursos.iterator();
        while (iterator.hasNext()) {
            Curso curso = iterator.next();
            if (curso.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
