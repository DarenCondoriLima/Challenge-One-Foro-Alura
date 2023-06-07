package com.alura.domain.topico;

import com.alura.domain.curso.Curso;
import com.alura.domain.curso.CursoRepository;
import com.alura.domain.respuesta.Respuesta;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne
	private Usuario autor;
	@ManyToOne
	private Curso curso;
	@OneToMany
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(String titulo, String mensaje, Curso curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.curso = curso;
	}

	public Topico(DatosRegistroTopico datosRegistroTopico,Usuario usuario,Curso curso) {
		this.titulo=datosRegistroTopico.titulo();
		this.mensaje=datosRegistroTopico.mensaje();
		this.autor=usuario;
		this.curso=curso;
	}
}
