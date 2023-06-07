package com.alura.controller;

import com.alura.domain.usuario.DatosRegistroUsuario;
import com.alura.domain.usuario.DatosRespuestaUsuario;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registroUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario= usuarioRepository.save(new Usuario(datosRegistroUsuario));
        URI url=uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaUsuario(usuario));
    }
    @GetMapping("/{id}")
    public ResponseEntity retornaDatosUsuario(@PathVariable Long id) {
        Usuario usuario= usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }
}
