package co.com.runt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "autores")
@EntityListeners(AuditingEntityListener.class) // Para hacer auditoria no configurada.
public class Autor {

	@Id
	@Column(name = "aut_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "aut_nom")
	private String nombre;

	@Column(name = "aut_ape")
	private String apellido;

	@Column(name = "aut_anionac")
	private Integer anioautor;

	@ManyToOne
	@JoinColumn(name = "gen_id")
	private Genero genero;

	//___getter y setter
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getAnioautor() {
		return anioautor;
	}

	public void setAnioautor(Integer anioautor) {
		this.anioautor = anioautor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
