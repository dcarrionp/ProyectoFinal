package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.DAO.LibroDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionLibros {

	@Inject
	private LibroDAO dao;
	
	public void setLibro(Libro li) {
		dao.insertLibro(li);
	}
	
	public void actualiar(Libro cliente) {
		dao.update(cliente);
	}
	
	public void borrar(String ci) {
		Libro cli = dao.read(ci);
		dao.delete(cli);
	}
	
	public List<Libro> getAll(){
		return dao.getAll();
	}
	
	public Libro getLibro(String nombre) throws Exception {
		Libro li =  dao.read(nombre);
		if(nombre.length()<1) {
			throw new Exception("Nombre Incorrecto");
		}
		if(li==null) {
			throw new Exception("Libro no existe");
		}
		return li;
	}
	
	public List<Libro> getCategoria(String cat){
		return dao.getxCategoria(cat);
	}
	
	public List<Libro> getAutor(String autor){
		return dao.getLibrosByAutor(autor);
	}
	
	public List<Libro> getDisponibilidad(boolean dispo){
		return dao.getLibrosByDisponibilidad(dispo);
	}
	
	public void reducirStock(Libro libro) throws Exception {
        if (libro.getStock() > 0) {
            libro.setStock(libro.getStock() - 1);
            dao.update(libro);
        } else {
            throw new Exception("No hay suficiente stock para este libro");
        }
    }
    
    public void aumentarStock(Libro libro) {
        libro.setStock(libro.getStock() + 1);
        dao.update(libro);
    }
}
