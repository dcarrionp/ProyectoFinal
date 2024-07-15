package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class CategoriaDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Categoria li) {
		em.persist(li);
	}

	public void update(Categoria li) {
		em.merge(li);
	}

	public void delete(String nombre) {
		Categoria li = this.read(nombre);
		em.remove(li);
	}

	public Categoria read(String nombre) {
		TypedQuery<Categoria> query = em.createQuery("SELECT l FROM Categoria l WHERE l.nombre = :nombre",
				Categoria.class);
		query.setParameter("nombre", nombre);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Categoria> getAll() {
		String jpql = "SELECT c FROM Categoria c ORDER BY codigo";
		Query query = em.createQuery(jpql, Categoria.class);
		return query.getResultList();
	}

}
