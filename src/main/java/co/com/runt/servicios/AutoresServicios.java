package co.com.runt.servicios;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.com.runt.entities.Autor;
import co.com.runt.entities.Genero;
import co.com.runt.entradas.Consulta;
import co.com.runt.excepcion.Respuesta;
import co.com.runt.repository.AutorRepository;
import co.com.runt.repository.GeneroRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController // clase que ofrece los metodos al exterior pormedio de http
@RequestMapping("/api") // asegura que la petición identificada por la URL sea manejada por el
						// correspondiente método
public class AutoresServicios {

	@Autowired // inyecta un objeto dentro de otro
	AutorRepository autorRepository;
	@Autowired
	GeneroRepository generoRepository;
    
	
	/**
	 * MUESTRA LISTADO DE AUTORES
	 * 
	 * @return
	 */
	@GetMapping("listautores") // petición para obetner datos
	public List<Autor> ListarAutores() {
		return autorRepository.findAll();
	}


	/**
	 * SERVICIO PARA GUARDAR EL AUTOR
	 * 
	 * @param autor
	 * @return
	 */
	
	@PostMapping("guardautores")
	public Respuesta creaAutor(@Valid @RequestBody Autor autor) {
		System.out.println(">>>> ENTRE AL SERVUCUI...");
		Respuesta respuesta = new Respuesta();

		//Integer nomYapeRepe = autorRepository.nomYapeDupli(autor.getNombre(), autor.getApellido());
		Optional<Genero> generoDB = generoRepository.findById(autor.getGenero().getId());

		if (autor.getNombre().length() > 15) {
			respuesta.setMensaje("Nombre: Acepta maximo 15 Caracteres !");
			respuesta.setCodigo(0);
		} else if (autor.getApellido().length() > 15) {
			respuesta.setMensaje("Apellido: Acepta maximo 15 Caracteres !");
			respuesta.setCodigo(0);
		}

		/*else if (nomYapeRepe > 0) {
			respuesta.setCodigo(0);
			respuesta.setMensaje(
					"Ya Existe un Autor con el Nombre y Apellido: " + autor.getNombre() + " " + autor.getApellido());
			return respuesta;
		}*/

		else if (generoDB.isPresent() == false) {
			respuesta.setMensaje("Error: el Genero no existe !");
			respuesta.setCodigo(0);
		} else {

			Genero genero = generoDB.get();
			autor.setGenero(genero);
			Autor autorGuardado = autorRepository.save(autor);

			if (autorGuardado != null) {

				HttpStatus.OK.value();
				respuesta.setCodigo(HttpStatus.OK.value());
				respuesta.setMensaje("Autor: Registrado Exitosamente !");
				respuesta.setAutor(autorGuardado);
			} else {
				HttpStatus.NOT_ACCEPTABLE.value();
				respuesta.setCodigo(0);
				respuesta.setMensaje("Error: al Guardar Autor !");
			}
			
		}
		return respuesta;
	}

	/**
	 * SERVICIO PARA CONSULTAR UN AUTOR POR ID
	 * 
	 * @param autorId IDENTIFICADOR DEL AUTOR A CONSULTAR
	 * @return
	 */

	@GetMapping("consultautor/{id}")
	public Respuesta consulXidautor(@PathVariable(value = "id") Long autorId) {
		Respuesta respuesta = new Respuesta();

		Optional<Autor> autorDB = autorRepository.findById(autorId);
		if (autorDB.isPresent()) {

			Autor autor = autorDB.get();
			respuesta.setCodigo(1);
			respuesta.setMensaje("Autor Encontrado");
			respuesta.setAutor(autor);

		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("Autor No Encontrado");
		}

		return respuesta;

	}

	/**
	 * SERVICIO PARA ACTULIZAR AUTOR
	 * 
	 * @param autorId      IDENTIFICADOR DEL AUTOR A ACTUALIZAR
	 * @param detalleAutor DATOS DEL AUTOR QUE SE ACTULIZA
	 * @return
	 */
	@PutMapping("actualizaAutor/{id}")
	public Respuesta actualizaautor(@PathVariable(value = "id") Long autorId, @Valid @RequestBody Autor detalleAutor) {

		Respuesta respuesta = new Respuesta();

		Integer nomYapeRepe = autorRepository.nomYapeDupli(detalleAutor.getNombre(), detalleAutor.getApellido());
		Optional<Autor> autorDB = autorRepository.findById(autorId);

		Optional<Genero> generoDB = generoRepository.findById(detalleAutor.getGenero().getId());

		if (detalleAutor.getNombre().length() > 15) {
			respuesta.setMensaje("Nombre: Acepta maximo 15 Caracteres !");

		}

		else if (detalleAutor.getApellido().length() > 15) {
			respuesta.setMensaje("Apellido: Acepta maximo 15 Caracteres !");
		}

		else if (nomYapeRepe > 0) {
			respuesta.setCodigo(0);
			respuesta.setMensaje("Ya Existe un Autor con el Nombre y Apellido: " + detalleAutor.getNombre() + " "
					+ detalleAutor.getApellido());
			return respuesta;
		}

		else if (generoDB.isPresent() == false) {
			respuesta.setCodigo(0);
			respuesta.setMensaje("Error: el Genero no existe !");

		} else {

			if (autorDB.isPresent()) {
				Autor actuautorEncontrado = autorDB.get();

				if (detalleAutor.getNombre() != null) {
					actuautorEncontrado.setNombre(detalleAutor.getNombre());
				}

				if (detalleAutor.getApellido() != null) {
					actuautorEncontrado.setApellido(detalleAutor.getApellido());
				}

				if (detalleAutor.getAnioautor() != null) {
					actuautorEncontrado.setAnioautor(detalleAutor.getAnioautor());
				}

				if (detalleAutor.getGenero() != null) {

					Genero genero = generoDB.get();
					actuautorEncontrado.setGenero(genero);
					// respuesta.setGenero(genero);
				}

				Autor actualizaautor = autorRepository.save(actuautorEncontrado);

				respuesta.setCodigo(1);
				respuesta.setMensaje("Autor Actualizado exitosamente !");
				respuesta.setAutor(actualizaautor);

			}

			else {
				respuesta.setCodigo(0);
				respuesta.setMensaje("El Autor no esta registrado");
			}
		}
		return respuesta;

	}

	/**
	 * SERVICIO PARA ELIMINAR
	 * 
	 * @param autorId EL ID DEL AUTOR A ELIMINAR
	 * @return
	 */
	@DeleteMapping("eliminarautor/{id}")

	public Respuesta eliminaAutor(@PathVariable(value = "id") Long autorId) {
		Respuesta respuesta = new Respuesta();

		Optional<Autor> autorDB = autorRepository.findById(autorId);
		if (autorDB.isPresent()) {

			Autor autor = autorDB.get();
			autorRepository.delete(autor);

			respuesta.setMensaje("Autor Eliminado Exitosamente !");
			respuesta.setCodigo(1);
			respuesta.setAutor(autor);

		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("No existe Autor para Eliminar");
		}

		return respuesta;
	}

	/**
	 * SERVICIO PARA CONSULTAR POR APELLIDO
	 * 
	 * @param consulta envio como para metro en el body "dato"
	 * @return
	 */
	@PostMapping("consultaAutorPorApellido")

	public Respuesta conautape(@Valid @RequestBody Consulta consulta) {
		Respuesta respuesta = new Respuesta();
		List<Autor> listaAutores = autorRepository.consautorXape("%" + consulta.getDato() + "%");

		if (listaAutores != null && listaAutores.size() > 0) {
			respuesta.setCodigo(1);
			respuesta.setListaAutores(listaAutores);

		} else {
			
			respuesta.setCodigo(0);
			respuesta.setMensaje("No se encontraron Registros con el Parametro de Busqueda !");
		}

		return respuesta;

	}


}
