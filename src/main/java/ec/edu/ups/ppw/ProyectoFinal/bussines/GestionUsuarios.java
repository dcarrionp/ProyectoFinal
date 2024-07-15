package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.DAO.UsuarioDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuarios {

	@Inject
	private UsuarioDAO dao;
	
	public void crear(Usuario us) {
		dao.insert(us);
	}
	
	public Usuario read(String correo) {
		return dao.read(correo);
	}
	
	public void update(Usuario us) {
		dao.update(us);
	}
	
	public void delete(String correo) {
		dao.delete(correo);
	}
	
	public List<Usuario> getAll(){
		return dao.getAll();
	}
}
