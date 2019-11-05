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
@Table(name = "tipo_documentos")
@EntityListeners(AuditingEntityListener.class)
public class Tipodocumentos {

	@Id
	@Column(name = "cod_tipdoc")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_tipdoc")
	private String nomtipdoc;

	@Column(name = "desc_tipdoc")
	private String desctipdoc;

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

	public String getDesctipdoc() {
		return desctipdoc;
	}

	public void setDesctipdoc(String desctipdoc) {
		this.desctipdoc = desctipdoc;
	}

}
