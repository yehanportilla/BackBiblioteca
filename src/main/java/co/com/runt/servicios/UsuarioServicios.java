package co.com.runt.servicios;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.runt.entities.Usuario;
import co.com.runt.entradas.Consulta;
import co.com.runt.excepcion.Respuesta;
import co.com.runt.repository.UsuarioRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsuarioServicios {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("listausuarios")
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@PostMapping("guardausuario")

	public Respuesta respuesta(@Valid @RequestBody Usuario detaUsuario) {
		Respuesta respuesta = new Respuesta();
		Usuario guardaUsuario = usuarioRepository.save(detaUsuario);
		if (guardaUsuario != null) {

			HttpStatus.OK.value();
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setMensaje("Usuario: Registrado Exitosamente !");
			respuesta.setUsuario(guardaUsuario);
		} else {
			HttpStatus.NOT_ACCEPTABLE.value();
			respuesta.setCodigo(HttpStatus.NOT_ACCEPTABLE.value());
			respuesta.setMensaje("Error: al Guardar Usuario !");
		}
		return respuesta;
	}

	@PostMapping("iniciosesion")

	public Respuesta coninisesion(@Valid @RequestBody Consulta consulta) {
		Respuesta respuesta = new Respuesta();

		List<Usuario> resconsesion = usuarioRepository.ingresar(consulta.getLogin(), consulta.getContrasenia());

		if (resconsesion != null && resconsesion.size() > 0) {

			respuesta.setCodigo(1);
			respuesta.setMensaje("Inicia sesion...!");
			respuesta.setListaUsuarios(resconsesion);

		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("No se encontraron Registros con el Parametro de Busqueda !");

		}
		return respuesta;

	}

}
