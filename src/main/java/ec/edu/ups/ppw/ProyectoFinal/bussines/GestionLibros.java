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

	public Libro getLibro(String nombre) throws Exception {
		if (nombre.length() < 1) {
			throw new Exception("Nombre incorrecto");
		}

		Libro libro = daoLibro.read(nombre);
		if (libro == null) {
			throw new Exception("Libro no existe");
		}
		return libro;
	}

	public List<Libro> getLibros() {
		return daoLibro.getAll();
	}

	public void createLibro(Libro libro) throws Exception {
		if (libro.getNombre().length() < 1) {
			throw new Exception("Nombre incorrecto");
		}
		daoLibro.insertLibro(libro);
	}

	public void updateLibro(Libro libro) throws Exception {
		if (libro.getNombre().length() < 1) {
			throw new Exception("Nombre incorrecto");
		}
		daoLibro.update(libro);
	}

	public void deleteLibro(String nombre) throws Exception {
		if (nombre.length() < 1) {
			throw new Exception("Nombre incorrecto");
		}
		Libro libro = daoLibro.read(nombre);
		if (libro == null) {
			throw new Exception("Libro no existe");
		}
		daoLibro.delete(nombre);
	}
}
