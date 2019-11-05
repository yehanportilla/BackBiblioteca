package co.com.runt.servicios;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.runt.entities.Genero;
import co.com.runt.excepcion.Respuesta;
import co.com.runt.repository.GeneroRepository;


@RestController // clase repetidora para las peticiones http
@RequestMapping("/api") // asegura que la petición identificada por la URL sea manejada por el
						// correspondiente método
public class GeneroServicios {

	@Autowired
	GeneroRepository generoRepository;

	/**
	 * MUESTRA LISTADO DE GENEROS
	 * 
	 * @return
	 */
	@GetMapping("listageneros")
	public List<Genero> listaGeneros() {
		return generoRepository.findAll();

	}

	@PostMapping("guardagenero")
	public Respuesta respuesta(@Valid @RequestBody Genero detaGenero) {
		Respuesta respuesta = new Respuesta();

		Genero generoguardado = generoRepository.save(detaGenero);

		if (generoguardado != null) {

			respuesta.setCodigo(1);
			respuesta.setMensaje("Genero: Registrado Exitosamente !");
			respuesta.setGenero(generoguardado);
		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("Error: al Guardar Genero !");

		}
		return respuesta;
	}

}
