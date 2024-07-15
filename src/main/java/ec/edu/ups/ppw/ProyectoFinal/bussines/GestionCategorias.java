package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.DAO.CategoriaDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Categoria;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCategorias {
	@Inject
	private CategoriaDAO dao;
	
	public void agregarCategoria(Categoria cat) {
		dao.insert(cat);
	}
	
	public void actualizar(Categoria cat) {
		dao.update(cat);
	}
	
	public void eliminar(String nombre) {
		dao.delete(nombre);
	}
	
	public Categoria getCategoria(String nombre) {
		return dao.read(nombre);
	}
	
	public List<Categoria> getAll(){
		return dao.getAll();
	}
}
