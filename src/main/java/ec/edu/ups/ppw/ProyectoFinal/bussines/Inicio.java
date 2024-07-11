package ec.edu.ups.ppw.ProyectoFinal.bussines;


import ec.edu.ups.ppw.ProyectoFinal.DAO.LibroDAO;
import ec.edu.ups.ppw.ProyectoFinal.DAO.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Inicio {
	
	@Inject
	private LibroDAO daoLibro;
	
	@Inject
	private PrestamoDAO daoPrestamo;
	
	@PostConstruct
	public void init() {
		System.out.println("Hola mundo EJB");
		
		
		Libro libro = new Libro();
		libro.setAutor("Diego");
		libro.setCategoria("Musica");
		libro.setEstado("Reservado");
		libro.setImagen(null);
		libro.setNombre("Mejores canciones de la decada");
		libro.setPrecio(20.00);
		
		daoLibro.insertLibro(libro);
		
		Prestamo pre = new Prestamo();
		
		pre.setLibro(libro);
		pre.setFechaDevolucion(null);
		pre.setFechaPrestamo(null);
		pre.setUsuario(null);
		
		daoPrestamo.insert(pre);
		
		
	}
	
}
