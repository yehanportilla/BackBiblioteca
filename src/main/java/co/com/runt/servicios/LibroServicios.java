package co.com.runt.servicios;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import co.com.runt.entities.Libro;
import co.com.runt.entradas.Consulta;
import co.com.runt.excepcion.Respuesta;
import co.com.runt.repository.AutorRepository;
import co.com.runt.repository.GeneroRepository;
import co.com.runt.repository.LibroRepository;

@RestController // clase repetidora para las peticiones http
@RequestMapping("/api") // asegura que la petición identificada por la URL sea manejada por el
						// correspondiente método

public class LibroServicios {

	@Autowired
	LibroRepository libroRepository;
	@Autowired
	GeneroRepository generoRepository;
	@Autowired
	AutorRepository autorRepository;

	/**
	 * LISTA TODOS LOS LIBROS
	 * 
	 * @return
	 */
	@GetMapping("listalibros")
	public List<Libro> ListarLibro() {
		return libroRepository.findAll();
	}

	/**
	 * SERVICIO PARA GUARDAR IN LIBRO
	 * 
	 * @param detalibro GUARDA LOS DETALLES DEL LIBRO
	 * @return
	 */
	@PostMapping("guardalibro")
	public Respuesta creaLibro(@Valid @RequestBody Libro detalibro) {

		Respuesta respuesta = new Respuesta();

		Optional<Genero> generoDB = generoRepository.findById(detalibro.getGenero().getId());
		Optional<Autor> autorDB = autorRepository.findById(detalibro.getAutor().getId());

		Integer tituloduplicado = libroRepository.libroRepetido(detalibro.getTitulo());

		if (detalibro.getTitulo().length() > 35) {
			respuesta.setMensaje("Titulo: Acepta maximo 35 Caracteres !");
			respuesta.setCodigo(0);
		}

		else if (tituloduplicado > 0) {

			respuesta.setCodigo(0);
			respuesta.setMensaje("Ya Existe un Libro con el titulo:" + detalibro.getTitulo());
			return respuesta;
		}

		else if (generoDB.isPresent() == false) {
			respuesta.setMensaje("Error: el Genero no existe !");
			respuesta.setCodigo(0);
		}

		else if (autorDB.isPresent() == false) {
			respuesta.setMensaje("Error: el Autor no existe !");
			respuesta.setCodigo(0);
		}

		else {
			Genero genero = generoDB.get();
			detalibro.setGenero(genero);

			Autor autor = autorDB.get();
			detalibro.setAutor(autor);

			Libro libroGuardado = libroRepository.save(detalibro);

			if (libroGuardado != null) {

				respuesta.setCodigo(1);
				respuesta.setMensaje("Libro: Registrado Exitosamente !");
				respuesta.setLibro(libroGuardado);

			} else {
				respuesta.setCodigo(0);
				respuesta.setMensaje("Error: al Guardar Libro !");
			}

		}

		return respuesta;
	}

	/**
	 * SERVICIO PARA CONSULTAR LIBRO POR ID
	 * 
	 * @param libroId ENVIO ID DEL LIBRO A BUSCAR.
	 * @return
	 */
	@GetMapping("consultalibro/{id}")
	public Respuesta consultaXidlibro(@PathVariable(value = "id") Long libroId) {

		Respuesta respuesta = new Respuesta();
		Optional<Libro> libroDB = libroRepository.findById(libroId);

		if (libroDB.isPresent()) {
			Libro libro = libroDB.get();

			respuesta.setCodigo(1);
			respuesta.setMensaje("Libro Encontrado !");
			respuesta.setLibro(libro);

		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("Error: el Autor no existe !");

		}

		return respuesta;
	}

	/**
	 * SERVICIO PARA ACTUALIZAR LIBRO
	 * 
	 * @param libroID   ENVIO ID DEL LIBRO PARA ACTUALIZAR
	 * @param detalibro ENVIO DETALLE DEL LIBRO A GUARDAR O ACTULIZAR
	 * @return
	 */
	@PutMapping("actualizalibro/{id}")
	public Respuesta actualizaXid(@PathVariable(value = "id") Long libroID, @Valid @RequestBody Libro detalibro) {
		Respuesta respuesta = new Respuesta();

		Optional<Libro> libroBD = libroRepository.findById(libroID);

		Optional<Genero> generoBD = generoRepository.findById(detalibro.getGenero().getId());
		Optional<Autor> autorBD = autorRepository.findById(detalibro.getAutor().getId());

		Integer tituloduplicado = libroRepository.libroRepetido(detalibro.getTitulo());
		
		if (detalibro.getTitulo().length() > 35) {
			respuesta.setMensaje("Titulo: Acepta maximo 35 Caracteres !");
			respuesta.setCodigo(0);
		}

		else if (tituloduplicado > 0) {

			respuesta.setCodigo(0);
			respuesta.setMensaje("Ya Existe un Libro con el titulo:" + detalibro.getTitulo());
			return respuesta;
		}

		else if (generoBD.isPresent() == false) {

			respuesta.setCodigo(0);
			respuesta.setMensaje("Error: el Genero no existe !");
		}

		else if (autorBD.isPresent() == false) {
			respuesta.setCodigo(0);
			respuesta.setMensaje("Error: el Autor no existe !");
		}

		else {

			if (libroBD.isPresent()) {

				Libro libroEncontrado = libroBD.get();

				if (detalibro.getTitulo() != null) {
					libroEncontrado.setTitulo(detalibro.getTitulo());
				}

				if (detalibro.getAñopublic() != null) {
					libroEncontrado.setAñopublic(detalibro.getAñopublic());
				}

				if (detalibro.getGenero() != null) {
					Genero genero = generoBD.get();
					libroEncontrado.setGenero(genero);
				}

				if (detalibro.getAutor() != null) {
					Autor autor = autorBD.get();
					libroEncontrado.setAutor(autor);

				}

				Libro actualizaXid = libroRepository.save(libroEncontrado);

				respuesta.setCodigo(1);
				respuesta.setMensaje("Libro Actualizado exitosamente !");
				respuesta.setLibro(actualizaXid);

			} else {
				respuesta.setCodigo(0);
				respuesta.setMensaje("El Libro No se encuentra Registrado !");
			}
		}
		return respuesta;

	}

	@DeleteMapping("eliminarlibro/{id}")
	public Respuesta eliminaLibro(@PathVariable(value = "id") Long libroID) {
		Respuesta respuesta = new Respuesta();
		Optional<Libro> libroBD = libroRepository.findById(libroID);

		if (libroBD.isPresent()) {

			Libro libro = libroBD.get();
			libroRepository.delete(libro);

			respuesta.setCodigo(1);
			respuesta.setLibro(libro);
			respuesta.setMensaje("El Libro Se Elimino Exitosamente !");

		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("El Libro No se encuentra Registrado !");

		}

		return respuesta;

	}

	/**
	 * SERVICIO PARA CONSULTA POR GENERO
	 * 
	 * @param consulta es el dato que va como parametro en el body.
	 * @return
	 */
	@PostMapping("consultalibroxgenero")
	public Respuesta ConlibroXgenero(@Valid @RequestBody Consulta consulta) {
		Respuesta respuesta = new Respuesta();

		List<Libro> listalibrogenero = libroRepository.consultarLibrosPorGenero(consulta.getDato());

		if (listalibrogenero != null && listalibrogenero.size() > 0) {
			respuesta.setCodigo(1);
			respuesta.setListaLibros(listalibrogenero);
		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("No se encontraron Registros con el Parametro de Busqueda !");

		}

		return respuesta;

	}

	@PostMapping("consultaxpartetitulo")
	public Respuesta ConXparteLibro(@Valid @RequestBody Consulta consulta) {
		Respuesta respuesta = new Respuesta();

		List<Libro> listalibroxtitulo = libroRepository.consultaXtitulo("%" + consulta.getDato() + "%");

		if (listalibroxtitulo != null && listalibroxtitulo.size() > 0) {
			respuesta.setCodigo(1);
			respuesta.setListaLibros(listalibroxtitulo);
		} else {
			respuesta.setCodigo(0);
			respuesta.setMensaje("No se encontraron Registros con el Parametro de Busqueda !");
		}

		return respuesta;

	}

}
