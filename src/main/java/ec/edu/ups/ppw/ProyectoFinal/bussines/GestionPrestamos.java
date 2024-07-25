package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.DAO.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPrestamos {
	
	@Inject
	PrestamoDAO dao;
	
	public void crear(Prestamo us) {
		dao.insert(us);
	}

	public Prestamo read(String correo) {
		return dao.read(correo);
	}

	public void update(Prestamo us) {
		dao.update(us);
	}

	public void delete(String correo) {
		dao.delete(correo);
	}

	public List<Prestamo> getAll(){
		return dao.getAll();
	}
	
	public List<Prestamo> getUsuario(String usuario){
		return dao.getUsuario(usuario);
	}

	public List<Prestamo> getLibro(String libro){
		return dao.getPrestamosByNombreLibro(libro);
	}
}
