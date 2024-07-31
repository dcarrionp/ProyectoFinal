package ec.edu.ups.ppw.ProyectoFinal.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Libro {

	@Id
	@GeneratedValue
	private int codigo;
	
	@Column(unique = true)
	private String nombre;
	private Double precio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	
	private String autor;
	private String imagen;
	private boolean estado;
	private int stock;
	
	@Transient
    private String categoriaNombre;
	
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "libro [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria
				+ ", autor=" + autor + ", imagen=" + imagen + ", disponibilidad=" + estado + ", stock=" + stock
				+ ", categoriaNombre=" + categoriaNombre + "]";
	}

}
