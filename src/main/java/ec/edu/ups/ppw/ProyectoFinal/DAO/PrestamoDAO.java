package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinal.Model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class PrestamoDAO {
	
	@PersistenceContext
    private EntityManager em;

    public void insert(Prestamo pre) {
        TypedQuery<Usuario> userQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :email", Usuario.class);
        userQuery.setParameter("email", pre.getUsuarioEmail());
        Usuario managedUsuario;
        try {
            managedUsuario = userQuery.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("El Usuario con el email " + pre.getUsuarioEmail() + " no existe.");
        }

        // 2. Buscar el libro en la base de datos usando el nombre del libro recibido
        TypedQuery<Libro> bookQuery = em.createQuery("SELECT l FROM Libro l WHERE l.nombre = :nombre", Libro.class);
        bookQuery.setParameter("nombre", pre.getLibroNombre());
        Libro managedLibro;
        try {
            managedLibro = bookQuery.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("El libro con el nombre " + pre.getLibroNombre() + " no existe.");
        }

        // 3. Asignar el Usuario y el libro gestionados al prestamo
        pre.setUsuario(managedUsuario);
        pre.setLibro(managedLibro);

        // 4. Persistir el prestamo
        em.persist(pre);
    }

    public void update(Prestamo us) {
        em.merge(us);
    }

    public Prestamo read(String codigo) {
        return em.find(Prestamo.class, codigo);
    }

    public List<Prestamo> getAll() {
        String jpql = "SELECT c FROM Prestamo c ORDER BY c.codigo";
        Query query = em.createQuery(jpql, Prestamo.class);
        return query.getResultList();
    }

    public void delete(String correo) {
        Prestamo us = this.read(correo);
        em.remove(us);
    }

    public List<Prestamo> getUsuario(String nombreUsuario) {
        String jpql = "SELECT p FROM Prestamo p JOIN p.usuario u WHERE u.usuario = :nombreUsuario ORDER BY p.codigo";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        return query.getResultList();
    }

    public List<Prestamo> getPrestamosByNombreLibro(String nombre) {
        String jpql = "SELECT p FROM Prestamo p JOIN p.libro l WHERE l.nombre = :nombre ORDER BY p.codigo";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }
	
}
