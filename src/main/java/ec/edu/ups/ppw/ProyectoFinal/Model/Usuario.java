package ec.edu.ups.ppw.ProyectoFinal.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	private int codigo;
	private String usuario;
	private boolean rol;
	
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
	public boolean isRol() {
		return rol;
	}
	public void setRol(boolean rol) {
		this.rol = rol;
	}

}
