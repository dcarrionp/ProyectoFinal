package ec.edu.ups.ppw.ProyectoFinal.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
public class Prestamo {

	@Id
	@GeneratedValue
	private int codigo;
	@Temporal(TemporalType.DATE)
	private LocalDate fechaInicio;
	@Temporal(TemporalType.DATE)
	private LocalDate fechaFin;
	private String estado;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "libro")
	private Libro libro;
	
	@Transient
    private String usuarioEmail; // Transient para el email del usuario

    @Transient
    private String libroNombre; // Transient para el nombre del libro
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	public String getLibroNombre() {
		return libroNombre;
	}
	public void setLibroNombre(String libroNombre) {
		this.libroNombre = libroNombre;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "prestamo [codigo=" + codigo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado="
				+ estado + ", usuario=" + usuario + ", libro=" + libro + "]";
	}
}
