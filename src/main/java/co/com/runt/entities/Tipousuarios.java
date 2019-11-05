package co.com.runt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tipo_usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Tipousuarios {

	@Id
	@Column(name = "id_tipousu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomtipdoc() {
		return nomtipdoc;
	}

	public void setNomtipdoc(String nomtipdoc) {
		this.nomtipdoc = nomtipdoc;
	}

	@Column(name = "nom_tipousu")
	private String nomtipdoc;

}
