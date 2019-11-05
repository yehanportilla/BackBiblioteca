package co.com.runt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.com.runt.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// JPQL PARA LOGIN
	@Query("select u from Usuario u where u.login=:login and u.contrasenia=:contrasenia")
	List<Usuario> ingresar(@Param("login") String loginUsu, @Param("contrasenia") String contraUsu);

}
