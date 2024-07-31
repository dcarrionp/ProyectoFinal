package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinal.Model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

        TypedQuery<Libro> bookQuery = em.createQuery("SELECT l FROM Libro l WHERE l.nombre = :nombre", Libro.class);
        bookQuery.setParameter("nombre", pre.getLibroNombre());
        Libro managedLibro;
        try {
            managedLibro = bookQuery.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("El Libro con el nombre " + pre.getLibroNombre() + " no existe.");
        }

        // Verificar stock antes de realizar el préstamo
        if (managedLibro.getStock() > 0) {
            managedLibro.setStock(managedLibro.getStock() - 1);
            em.merge(managedLibro);
        } else {
            throw new IllegalArgumentException("No hay suficiente stock para el Libro: " + managedLibro.getNombre());
        }

        pre.setUsuario(managedUsuario);
        pre.setLibro(managedLibro);

        em.persist(pre);
    }
	
	public void update(Prestamo pre) {
        if (pre.getEstado().equals("Devuelto")) {
            Libro Libro = pre.getLibro();
            Libro.setStock(Libro.getStock() + 1);
            em.merge(Libro);
        }
        em.merge(pre);
    }
	
	public Prestamo read(String codigo) {
		return em.find(Prestamo.class, codigo);
	}
	
	public List<Prestamo> getAll(){
		String jpql = "SELECT c FROM Prestamo c ORDER BY codigo";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, Prestamo.class);
		return query.getResultList();
	}
	
	public void delete(String correo) {
		Prestamo us = this.read(correo);
		em.remove(us);
	}
	
	public List<Prestamo> getUsuario(String nombreUsuario){
		String jpql = "SELECT p FROM Prestamo p JOIN p.usuario u WHERE u.usuario = :nombreUsuario ORDER BY p.codigo";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        return query.getResultList();
	}
	
	public List<Prestamo> getPrestamosByNombreLibro(String nombre) {
        String jpql = "SELECT p FROM Prestamo p JOIN p.Libro l WHERE l.nombre = :nombre ORDER BY p.codigo";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }
	
	public List<Usuario> getUsuariosConPrestamos() {
        String jpql = "SELECT DISTINCT p.usuario FROM Prestamo p";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    // Obtener los préstamos por Usuario
    public List<Prestamo> getPrestamosPorUsuario(Usuario user) {
        String jpql = "SELECT p FROM Prestamo p WHERE p.usuario = :usuario";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        query.setParameter("usuario", user);
        return query.getResultList();
    }
    
    public Libro getLibroByName(String nombre) {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.nombre = :nombre", Libro.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // O lanza una excepción si prefieres manejarlo de otra forma
        }
    }
    
    public long getCountReservasByLibro(String nombreLibro) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(p) FROM Prestamo p WHERE p.libro.nombre = :nombreLibro AND p.estado = 'Reservado'", Long.class);
        query.setParameter("nombreLibro", nombreLibro);
        return query.getSingleResult();
    }
    
    public List<Prestamo> getReservasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        TypedQuery<Prestamo> query = em.createQuery(
            "SELECT p FROM Prestamo p WHERE p.fechaInicio >= :fechaInicio AND p.fechaFin <= :fechaFin", 
            Prestamo.class
        );
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

}
