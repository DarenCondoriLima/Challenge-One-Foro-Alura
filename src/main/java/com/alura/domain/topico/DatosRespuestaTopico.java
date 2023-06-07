package com.alura.domain.topico;

import com.alura.domain.curso.Curso;
import com.alura.domain.curso.DatosRespuestaCurso;
import com.alura.domain.usuario.DatosRespuestaUsuario;
import com.alura.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo, String mensaje, LocalDateTime fechaCreacion, StatusTopico status, DatosRespuestaUsuario usuario,
        DatosRespuestaCurso curso
        ) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),
                new DatosRespuestaUsuario(topico.getAutor().getNombre(),topico.getAutor().getEmail())
                ,new DatosRespuestaCurso(topico.getCurso().getNombre(),topico.getCurso().getCategoria()));
    }
}
