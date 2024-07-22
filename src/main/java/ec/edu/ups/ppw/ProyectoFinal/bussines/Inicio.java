package ec.edu.ups.ppw.ProyectoFinal.bussines;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ec.edu.ups.ppw.ProyectoFinal.DAO.CategoriaDAO;
import ec.edu.ups.ppw.ProyectoFinal.DAO.LibroDAO;
import ec.edu.ups.ppw.ProyectoFinal.DAO.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinal.DAO.UsuarioDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Categoria;
import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinal.Model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Inicio {
	
	@Inject
	private CategoriaDAO gc;
	
	@Inject
	private LibroDAO gs;
	
	@Inject
	private UsuarioDAO gu;
	
	@Inject
	private PrestamoDAO gp;
	
	@PostConstruct
	public void init() {
		System.out.println("----------------INICIANDO----------------");
		
		// Categorias de libros a insertar
		String[] categorias = {"Drama", "Ciencia Ficcion", "Fantasia", "Historia", "Biografia",
		                       "Misterio", "Romance", "Aventura", "Terror", "Poesia", "Auto Ayuda"};

		// Bucle para crear e insertar cada categoria
		for (String nombreCategoria : categorias) {
		    Categoria cat = new Categoria();  
		    cat.setNombre(nombreCategoria);   
		    gc.insert(cat);                   
		}

		
		Libro li = new Libro();
		
		li.setNombre("Where the eyes can't go");
		li.setPrecio(15.0);
		li.setCategoria(gc.read("Terror"));
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/owl-s-quill.appspot.com/o/images%2FIMG_20220129_180131_682.jpg?alt=media&token=414ae1af-0000-45eb-8f8f-9706f1a95dec");
		li.setEstado(true);
		li.setAutor("Stephen King");
		System.out.println(li.toString());
		
		gs.insertLibro(li);
		
		Usuario us = new Usuario();
		us.setUsuario("dcarrionp");
		us.setRol("admin");
		
		gu.insert(us);
		
		us = new Usuario();
		us.setUsuario("SanDiego");
		us.setRol("common");
		
		gu.insert(us);
		
		Prestamo pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFin = LocalDate.parse("2024-07-17", formatter);

		pre.setFechaFin(fechaFin);
		pre.setLibro(li);
		pre.setUsuario(us);
		pre.setEstado("Reservado");

		gp.insert(pre);
		
	}
	
}
