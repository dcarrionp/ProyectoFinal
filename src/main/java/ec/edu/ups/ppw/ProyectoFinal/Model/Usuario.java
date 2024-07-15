package ec.edu.ups.ppw.ProyectoFinal.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	private String usuario;
	private String rol;
	
	
	@OneToMany(cascade =  CascadeType.ALL)
	@JoinColumn(name = "historial")
	private List<Prestamo> historial;
	
	public List<Prestamo> getHistorial() {
		return historial;
	}
	public void setHistorial(List<Prestamo> historial) {
		this.historial = historial;
	}
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
	public void setRol(String string) {
		this.rol = string;
	}

}
