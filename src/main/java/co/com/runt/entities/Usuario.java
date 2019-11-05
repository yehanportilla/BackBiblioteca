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
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	@Id
	@Column(name = "id_usu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login")
	private String login;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "numdocumento")
	private String numdocumento;

	@ManyToOne
	@JoinColumn(name = "cod_tipdoc")
	private Tipodocumentos tipodoc;

	@ManyToOne
	@JoinColumn(name = "id_tipousu")
	private Tipousuarios tipousu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNumdocumento() {
		return numdocumento;
	}

	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	public Tipodocumentos getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(Tipodocumentos tipodoc) {
		this.tipodoc = tipodoc;
	}

	public Tipousuarios getTipousu() {
		return tipousu;
	}

	public void setTipousu(Tipousuarios tipousu) {
		this.tipousu = tipousu;
	}

}
