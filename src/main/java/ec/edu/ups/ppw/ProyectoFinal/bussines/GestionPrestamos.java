package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.DAO.LibroDAO;
import ec.edu.ups.ppw.ProyectoFinal.DAO.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPrestamos {
	@Inject
	private PrestamoDAO prestamoDAO;

	@Inject
	private LibroDAO libroDAO;

	public void registrarPrestamo(Prestamo prestamo) throws Exception {
		Libro libro = prestamo.getLibro();
		if (!"disponible".equals(libro.getEstado())) {
			throw new Exception("El libro no está disponible");
		}
		libro.setEstado("prestado");
		libroDAO.update(libro);
		prestamo.setFechaPrestamo(new Date());
		prestamoDAO.insert(prestamo);
	}

	public void registrarDevolucion(Integer prestamoId) throws Exception {
		Prestamo prestamo = prestamoDAO.read(prestamoId);
		if (prestamo == null) {
			throw new Exception("Préstamo no encontrado");
		}
		Libro libro = prestamo.getLibro();
		libro.setEstado("disponible");
		libroDAO.update(libro);
		prestamo.setFechaDevolucion(new Date());
		prestamoDAO.update(prestamo);
	}

	public List<Prestamo> listarPrestamos() {
		return prestamoDAO.getAll();
	}
}
