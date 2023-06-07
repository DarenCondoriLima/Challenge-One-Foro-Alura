package com.alura.domain.topico;

import com.alura.domain.curso.CategoriasCurso;
import com.alura.domain.curso.Curso;
import com.alura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
    @NotBlank
    String titulo,
    @NotBlank
    String mensaje,
    @NotNull
    Long autor,
    @NotNull
    Long curso
){}
