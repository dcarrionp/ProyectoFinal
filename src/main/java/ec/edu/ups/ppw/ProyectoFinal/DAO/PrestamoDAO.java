package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PrestamoDAO {
	@PersistenceContext
    private EntityManager em;

    public void insert(Prestamo prestamo) {
        em.persist(prestamo);
    }

    public void update(Prestamo prestamo) {
        em.merge(prestamo);
    }

    public Prestamo read(Integer id) {
        return em.find(Prestamo.class, id);
    }

    public List<Prestamo> getAll() {
        return em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
    }
}
