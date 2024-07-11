package ec.edu.ups.ppw.ProyectoFinal.DAO;

import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class LibroDAO {
	
	@Inject
	private EntityManager em;
	
	public void insertLibro(Libro li) {
		em.persist(li);
	}
	
	public void update(Libro li) {
		em.merge(li);
	}
	
	public void delete(Libro li) {
		em.remove(li);
	}
	
	public void read(String li) {
		
	}

}
