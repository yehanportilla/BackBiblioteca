package co.com.runt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.runt.entities.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>  {

}
