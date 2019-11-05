package co.com.runt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.runt.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

	// ___consulta para libro con titulo repetido
	@Query("select count(b) from Libro b where b.titulo=:titulo")
	Integer libroRepetido(@Param("titulo") String tituloLibro);

	// _____INDICAR LOS LIBROS DE UN GENERO PARTICULAR
	@Query("SELECT l from Libro l JOIN l.genero g WHERE g.descripcion =:genero")
	List<Libro> consultarLibrosPorGenero(@Param("genero") String generoDesc);

	// ___INDICAR LOS LIBROS QUE EN SU TITULO CONTENGAN PARTE DEL TITULO
	@Query("SELECT l from Libro l WHERE l.titulo LIKE :Ptitulo")
	List<Libro> consultaXtitulo(@Param("Ptitulo") String tituLibro);

}
