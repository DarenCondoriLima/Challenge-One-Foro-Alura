package com.alura.domain.usuario;

import com.alura.domain.respuesta.Respuesta;
import com.alura.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String contrasena;

	@OneToMany(mappedBy = "autor")
	private List<Topico> topicos = new ArrayList<>();
	@OneToMany(mappedBy = "autor")
	private List<Respuesta> respuestas = new ArrayList<>();

	public Usuario(DatosRegistroUsuario datosRegistroUsuario){
		this.nombre= datosRegistroUsuario.nombre();
		this.email= datosRegistroUsuario.email();
		this.contrasena= datosRegistroUsuario.contrasena();
	}



	public void actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {
		if (datosActualizarUsuario.nombre()!=null){
			this.nombre= datosActualizarUsuario.nombre();
		}
		if (datosActualizarUsuario.email()!=null){
			this.email= datosActualizarUsuario.email();
		}
	}

	public void agregarTopico(Topico topico){
		this.topicos.add(topico);
	}

	public void agregarRespuesta(Respuesta respuesta) {
		this.respuestas.add(respuesta);
	}

	public void actualizarContrasena(DatosActualizarContrasena datosActualizarContrasena) {
		if (datosActualizarContrasena.contrasenaActual().equals(this.contrasena)){
			this.contrasena=datosActualizarContrasena.contrasenaNueva();
		}else {
			System.out.println("Las contraseñas no coinciden");
		}
	}
}
