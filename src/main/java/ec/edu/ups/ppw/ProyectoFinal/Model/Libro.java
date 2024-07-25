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
	@JoinColumn(name = "Categoria")
	private Categoria Categoria;
	private String autor;
	private String imagen;
	private Boolean estado;
	
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
		return Categoria;
	}
	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	/*@OneToMany(mappedBy = "libro", fetch = FetchType.EAGER)
	@JsonManagedReference
    private List<prestamo> prestamos;*/

}
