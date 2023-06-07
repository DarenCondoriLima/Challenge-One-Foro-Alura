package com.alura.domain.curso;

public record DatosRespuestaCurso(
        String nombre,
        CategoriasCurso categoria
){
    public DatosRespuestaCurso(Curso curso){
        this(curso.getNombre(),curso.getCategoria());
    }
}
