package com.alura.domain.respuesta;

import com.alura.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosRespuesta (String mensaje, LocalDateTime fechaCreacion, DatosRespuestaUsuario autor){
    public DatosRespuesta(Respuesta respuesta) {
        this(respuesta.getMensaje(),respuesta.getFechaCreacion(),
                new DatosRespuestaUsuario(respuesta.getAutor().getNombre(),respuesta.getAutor().getEmail()));
    }
}
