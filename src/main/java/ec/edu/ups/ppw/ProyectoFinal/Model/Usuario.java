package ec.edu.ups.ppw.ProyectoFinal.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int codigo;

	@Column(unique = true)
	private String usuario;
	private String rol;

	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	private List<Prestamo> historial;

	public List<Prestamo> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Prestamo> historial) {
		this.historial = historial;
	}*/

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String isRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "usuario [codigo=" + codigo + ", usuario=" + usuario + ", rol=" + rol + "]";
	}

	public String getRol() {
		return rol;
	}

}
