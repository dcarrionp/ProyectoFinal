package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.DAO.LibroDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionLibros {

	@Inject
	private LibroDAO daoLibro;

	public void setLibro(Libro li) {
		daoLibro.insertLibro(li);
	}
	
	public void actualiar(Libro cliente) {
		daoLibro.update(cliente);
	}
	
	public void borrar(String ci) {
		Libro cli = daoLibro.read(ci);
		daoLibro.delete(cli);
	}
	
	public List<Libro> getAll(){
		return daoLibro.getAll();
	}
	
	public Libro getLibro(String nombre) throws Exception {
		Libro li =  daoLibro.read(nombre);
		if(nombre.length()<1) {
			throw new Exception("Nombre Incorrecto");
		}
		if(li==null) {
			throw new Exception("Libro no existe");
		}
		return li;
	}
	
	
	public List<Libro> getCategoria(String cat){
		return daoLibro.getxCategoria(cat);
	}

	public List<Libro> getAutor(String autor){
		return daoLibro.getLibrosByAutor(autor);
	}

	public List<Libro> getDisponibilidad(boolean dispo){
		return daoLibro.getLibrosByDisponibilidad(dispo);
	}
}
