package com.alura.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Enumerated(EnumType.STRING)
	private CategoriasCurso categoria;

	@OneToMany
	private List<Topico> topicos = new ArrayList<>();

	public Curso(String nombre, CategoriasCurso categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}

}
