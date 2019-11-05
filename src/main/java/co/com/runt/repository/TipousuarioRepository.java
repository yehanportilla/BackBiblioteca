package co.com.runt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.runt.entities.Tipousuarios;

@Repository
public interface TipousuarioRepository extends JpaRepository<Tipousuarios, Long> {

}
