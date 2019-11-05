
package co.com.runt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.runt.entities.Tipodocumentos;

@Repository
public interface TipodocumentoRepository extends JpaRepository<Tipodocumentos, Long> {

}
