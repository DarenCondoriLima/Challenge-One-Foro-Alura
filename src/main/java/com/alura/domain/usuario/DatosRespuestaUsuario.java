package com.alura.domain.usuario;

public record DatosRespuestaUsuario(String nombre, String email) {

    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail());
    }
}
