package com.alura.controller;

import com.alura.domain.curso.Curso;
import com.alura.domain.curso.CursoRepository;
import com.alura.domain.curso.DatosRegistroCurso;
import com.alura.domain.curso.DatosRespuestaCurso;
import com.alura.domain.usuario.DatosRespuestaUsuario;
import com.alura.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registroCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
        URI url = uriComponentsBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaCurso(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity retornaDatosCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaCurso(curso));
    }
}