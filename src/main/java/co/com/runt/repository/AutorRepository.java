package co.com.runt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.com.runt.entities.Autor; // importar manual

/**
 * 
 * Clase intermedia entre la aplicación y la base de datos
 * 
 * @author ARI YEHAN
 *
 */

@Repository // encargado de almacenar datos en una base de datos o repositorio de
			// información que se necesite
public interface AutorRepository extends JpaRepository<Autor, Long> {

	// ____________JPQL PARA NOMBRE Y APELLIDO REPETIDO
	@Query("select count(a) from Autor a where a.nombre=:nombre and a.apellido=:apellido")
	Integer nomYapeDupli(@Param("nombre") String nombreAut, @Param("apellido") String apellidoAut);

	// ___JPQL PARA BUSCAR POR APELLIDO
	@Query("select b from Autor b where b.apellido LIKE :apellido")
	List<Autor> consautorXape(@Param("apellido") String apellidoAutor);

}
