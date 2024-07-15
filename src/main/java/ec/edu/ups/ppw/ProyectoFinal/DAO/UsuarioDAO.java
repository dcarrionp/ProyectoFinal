package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Usuario us) {
		if (em.find(Usuario.class, us.getCodigo()) != null) {
			throw new IllegalArgumentException("El usuario con el ID " + us.getCodigo() + " ya existe.");
		}
		em.persist(us);
	}
	
	public void update(Usuario us) {
		em.merge(us);
	}
	
	public Usuario read(String usuario) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class);
        query.setParameter("usuario", usuario);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
	
	public List<Usuario> getAll(){
		String jpql = "SELECT u FROM Usuario u ORDER BY u.codigo"; // Corregir la consulta JPQL
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
	
	public void delete(String correo) {
		Usuario us = this.read(correo);
		if (us != null) {
			em.remove(us);
		}
	}
}
