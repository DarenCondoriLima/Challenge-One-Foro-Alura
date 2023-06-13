package com.alura.controller;

import com.alura.domain.respuesta.*;
import com.alura.domain.topico.Topico;
import com.alura.domain.topico.TopicoRepository;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuesta> registroRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta
            , UriComponentsBuilder uriComponentsBuilder){
        Topico topico= topicoRepository.getReferenceById(datosRegistroRespuesta.topico());
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroRespuesta.autor());
        Respuesta respuesta= respuestaRepository.save(new Respuesta(datosRegistroRespuesta,topico, usuario));
        topico.agregarRespuesta(respuesta);
        usuario.agregarRespuesta(respuesta);
        URI url= uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuesta(respuesta));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuesta> retornarDatosRespuesta(@PathVariable Long id){
        Respuesta respuesta= respuestaRepository.getReferenceById(id);
        return  ResponseEntity.ok(new DatosRespuesta((respuesta)));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuesta>> listarRespuestas(Pageable pageable){
        return ResponseEntity.ok(respuestaRepository.findAll(pageable).map(DatosRespuesta::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuesta> actualizarRespuesta(@PathVariable Long id,@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta= respuestaRepository.getReferenceById(id);
        respuesta.actualizarRespuesta(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta= respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return  ResponseEntity.noContent().build();
    }

}
