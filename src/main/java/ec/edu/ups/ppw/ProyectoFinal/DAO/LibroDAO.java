package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class LibroDAO {

	@PersistenceContext
	private EntityManager em;

	public void insertLibro(Libro li) {
		em.persist(li);
	}

	public void update(Libro li) {
		em.merge(li);
	}

	public void delete(String nombre) {
		Libro li = this.read(nombre);
		em.remove(li);
	}

	public Libro read(String li) {
		TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.nombre = :nombre", Libro.class);
		query.setParameter("nombre", li);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Libro> getAll() {
		String jpl = "SELECT l FROM Libro l";
		Query query = em.createQuery(jpl, Libro.class);
		return query.getResultList();
	}

}
