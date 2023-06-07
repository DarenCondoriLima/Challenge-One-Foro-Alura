package com.alura.domain.respuesta;

import com.alura.domain.topico.Topico;
import com.alura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	@ManyToOne
	private Topico topico;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@ManyToOne
	private Usuario autor;
	private Boolean solucion = false;

}