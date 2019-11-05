package co.com.runt.excepcion;

import java.util.List;

import co.com.runt.entities.Autor;
import co.com.runt.entities.Genero;
import co.com.runt.entities.Libro;
import co.com.runt.entities.Usuario;

public class Respuesta {
	private String mensaje;
	private Integer codigo;
	private Autor autor;
	private Libro libro;
	private Genero genero;
	private Usuario usuario;

	private List<Autor> listaAutores;
	private List<Libro> listaLibros;
	private List<Usuario> listaUsuarios;

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(List<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public List<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
