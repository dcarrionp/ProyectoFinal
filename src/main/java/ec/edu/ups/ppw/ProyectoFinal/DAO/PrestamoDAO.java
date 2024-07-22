package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class PrestamoDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void insert(Prestamo pre) {
		em.persist(pre);
	}

	public void update(Prestamo us) {
		em.merge(us);
	}

	public Prestamo read(String codigo) {
		return em.find(Prestamo.class, codigo);
	}

	public List<Prestamo> getAll(){
		String jpql = "SELECT c FROM prestamo c ORDER BY codigo";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, Prestamo.class);
		return query.getResultList();
	}

	public void delete(String correo) {
		Prestamo us = this.read(correo);
		em.remove(us);
	}
	
}
