package com.alura.controller;

import com.alura.domain.curso.Curso;
import com.alura.domain.curso.CursoRepository;
import com.alura.domain.curso.DatosRespuestaCurso;
import com.alura.domain.topico.DatosRegistroTopico;
import com.alura.domain.topico.DatosRespuestaTopico;
import com.alura.domain.topico.Topico;
import com.alura.domain.topico.TopicoRepository;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registroTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroTopico.autor());
        Curso curso = cursoRepository.getReferenceById(datosRegistroTopico.curso());
        System.out.println(usuario.getNombre() + curso.getNombre());
        Topico topico= topicoRepository.save(new Topico(datosRegistroTopico,usuario,curso));
        usuario.agregarTopico(topico);
        curso.agregarTopico(topico);
        URI url= uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico));
    }
    /*@GetMapping("/{id}")
    public ResponseEntity retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }*/
}
