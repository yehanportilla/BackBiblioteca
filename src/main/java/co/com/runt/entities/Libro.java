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
@Table(name="libros")
@EntityListeners(AuditingEntityListener.class) // Para hacer auditoria.
public class Libro {
	
@ Id
@Column(name="lib_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name="lib_titulo")
private String titulo;

@Column(name="lib_aniopublic")
private Integer añopublic;

@ManyToOne
@JoinColumn(name = "gen_id")
private Genero genero;

@ManyToOne
@JoinColumn(name="aut_id")
private Autor autor;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public Integer getAñopublic() {
	return añopublic;
}

public void setAñopublic(Integer añopublic) {
	this.añopublic = añopublic;
}

public Genero getGenero() {
	return genero;
}

public void setGenero(Genero genero) {
	this.genero = genero;
}

public Autor getAutor() {
	return autor;
}

public void setAutor(Autor autor) {
	this.autor = autor;
}

}
