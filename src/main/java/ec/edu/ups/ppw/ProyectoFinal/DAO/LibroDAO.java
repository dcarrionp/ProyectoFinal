package ec.edu.ups.ppw.ProyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Categoria;
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
		TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c WHERE c.nombre = :nombre", Categoria.class);
        query.setParameter("nombre", li.getCategoriaNombre());
        Categoria managedCategoria;
        try {
            managedCategoria = query.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("La categoría con el nombre " + li.getCategoriaNombre() + " no existe.");
        }

        // Asignar la categoría gestionada al Libro
        li.setCategoria(managedCategoria);

        // Persistir el Libro
        em.persist(li);
    }
	
	public void update(Libro li) {
		String categoriaNombre = li.getCategoriaNombre();
	    System.out.println("Nombre de la categoría: " + categoriaNombre);
        TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c WHERE c.nombre = :nombre", Categoria.class);
        query.setParameter("nombre", li.getCategoriaNombre());
        Categoria managedCategoria;
        try {
            managedCategoria = query.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("La categoría con el nombre " + li.getCategoriaNombre() + " no existe.");
        }
        
        li.setCategoria(managedCategoria);
        em.merge(li);
	}
	
	public void delete(Libro li) {
		em.remove(li);
	}
	
	public Libro read(String nombre) {
		TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.nombre = :nombre", Libro.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
	
	public List<Libro> getAll(){
		String jpql = "SELECT c FROM Libro c ORDER BY codigo";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, Libro.class);
		return query.getResultList();
	}
	
	public List<Libro> getxCategoria(String cat){
		String jpql = "SELECT l FROM Libro l JOIN l.Categoria c WHERE c.nombre = :CategoriaNombre ORDER BY l.codigo";
	    TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
	    query.setParameter("CategoriaNombre", cat);
	    return query.getResultList();
	}
	
	public List<Libro> getLibrosByAutor(String autor) {
	    String jpql = "SELECT l FROM Libro l WHERE l.autor = :autor ORDER BY l.codigo";
	    TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
	    query.setParameter("autor", autor);
	    return query.getResultList();
	}
	
	public List<Libro> getLibrosByDisponibilidad(boolean disponibilidad) {
	    String jpql = "SELECT l FROM Libro l WHERE l.disponibilidad = :disponibilidad ORDER BY l.codigo";
	    TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
	    query.setParameter("disponibilidad", disponibilidad);
	    return query.getResultList();
	}
	
	public void desactivarLibro(int codigo) {
        Libro libro = em.find(Libro.class, codigo);
        if (libro != null) {
            libro.setEstado(false);
            em.merge(libro);
        }
    }

    public void activarLibro(int codigo) {
        Libro libro = em.find(Libro.class, codigo);
        if (libro != null) {
            libro.setEstado(true);
            em.merge(libro);
        }
    }

}
