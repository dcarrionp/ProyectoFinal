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
		String[] categorias = {"Drama", "Ciencia Ficcion", "Fantasia", "Historia", "Biografia",
		                       "Misterio", "Romance", "Aventura", "Terror", "Poesia", "Auto Ayuda"};
		for (String nombreCategoria : categorias) {
		    Categoria cat = new Categoria();  
		    cat.setNombre(nombreCategoria);   
		    gc.insert(cat);                   
		}

		
		Libro li = new Libro();
		
		li.setNombre("Where the eyes can't go");
		li.setPrecio(15.0);
		li.setCategoriaNombre("Terror");
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/owl-s-quill.appspot.com/o/images%2FIMG_20220129_180131_682.jpg?alt=media&token=414ae1af-0000-45eb-8f8f-9706f1a95dec");
		li.setEstado(true);
		li.setAutor("Stephen King");
		System.out.println(li.toString());
		gs.insertLibro(li);
		
		li = new Libro();
		li.setAutor("Harper Lee");
		li.setNombre("To Kill a Mockingbird");
		li.setCategoriaNombre("Drama");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/81aY1lxk+9L._AC_UF1000,1000_QL80_.jpg");
		li.setPrecio(25.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("George Orwell");
		li.setNombre("1984");
		li.setCategoriaNombre("Ciencia Ficcion");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/71kxa1-0mfL.jpg");
		li.setPrecio(30.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("J.K. Rowling");
		li.setNombre("Harry Potter and the Sorcerer's Stone");
		li.setCategoriaNombre("Fantasia");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/91HHqVTAJQL.jpg");
		li.setPrecio(35.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Anne Frank");
		li.setNombre("The Diary of a Young Girl");
		li.setCategoriaNombre("Biografia");
		li.setEstado(true);
		li.setImagen("https://images.thenile.io/r1000/9780241952443.jpg");
		li.setPrecio(20.00);
		System.out.println(li.toString());
		gs.insertLibro(li);
		
		li = new Libro();
		li.setAutor("Julio Cortazar");
		li.setNombre("Rayuela");
		li.setCategoriaNombre("Historia");
		li.setEstado(true);
		li.setImagen("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Rayuela_JC.png/640px-Rayuela_JC.png");
		li.setPrecio(20.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Arthur Conan Doyle");
		li.setNombre("Sherlock Holmes: The Complete Collection");
		li.setCategoriaNombre("Misterio");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/91XHDtSt7qL._AC_UF1000,1000_QL80_.jpg");
		li.setPrecio(40.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Herman Melville");
		li.setNombre("Moby Dick");
		li.setCategoriaNombre("Aventura");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/61PBBKyZ1rL._AC_UF1000,1000_QL80_.jpg");
		li.setPrecio(28.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Mary Shelley");
		li.setNombre("Frankenstein");
		li.setCategoriaNombre("Terror");
		li.setEstado(true);
		li.setImagen("https://mpd-biblio-covers.imgix.net/9780812551501.jpg");
		li.setPrecio(22.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Robert Frost");
		li.setNombre("The Poetry of Robert Frost");
		li.setCategoriaNombre("Poesia");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/81udWYEXefL.jpg");
		li.setPrecio(18.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Jane Austen");
		li.setNombre("Pride and Prejudice");
		li.setCategoriaNombre("Romance");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/81-vL6r8+7L.jpg");
		li.setPrecio(25.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new Libro();
		li.setAutor("Dale Carnegie");
		li.setNombre("How to Win Friends and Influence People");
		li.setCategoriaNombre("Auto Ayuda");
		li.setEstado(true);
		li.setImagen("https://m.media-amazon.com/images/I/71UwSHSZRnS.jpg");
		li.setPrecio(20.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		
		
		Usuario us = new Usuario();
		us.setUsuario("diegoandrescarrion123@gmail.com");
		us.setRol("admin");
		
		gu.insert(us);
		
		us = new Usuario();
		us.setUsuario("dcportilla2003@gmail.com");
		us.setRol("common");
		
		gu.insert(us);

		// Insertar usuarios
		us = new Usuario();
		us.setUsuario("usuario1@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario2@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario3@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario4@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario5@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario6@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario7@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario8@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario9@example.com");
		us.setRol("common");
		gu.insert(us);

		us = new Usuario();
		us.setUsuario("usuario10@example.com");
		us.setRol("common");
		gu.insert(us);

		
		Prestamo pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFin = LocalDate.parse("2024-07-17", formatter);

		pre.setFechaFin(fechaFin);
		pre.setLibroNombre("Rayuela");
		pre.setUsuarioEmail("dcportilla2003@gmail.com");
		pre.setEstado("Reservado");

		gp.insert(pre);
		
		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());
		fechaFin = LocalDate.parse("2024-08-23", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("To Kill a Mockingbird");
		pre.setUsuarioEmail("usuario1@example.com");
		pre.setEstado("Reservado");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.parse("2024-06-13", formatter));
		fechaFin = LocalDate.parse("2024-07-13", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("1984");
		pre.setUsuarioEmail("usuario2@example.com");
		pre.setEstado("Atrasado");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());
		fechaFin = LocalDate.parse("2024-09-01", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("Harry Potter and the Sorcerer's Stone");
		pre.setUsuarioEmail("usuario3@example.com");
		pre.setEstado("Disponible");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.parse("2024-07-01", formatter));
		fechaFin = LocalDate.parse("2024-07-30", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("The Diary of a Young Girl");
		pre.setUsuarioEmail("usuario4@example.com");
		pre.setEstado("Reservado");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());
		fechaFin = LocalDate.parse("2024-10-10", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("Sherlock Holmes: The Complete Collection");
		pre.setUsuarioEmail("usuario5@example.com");
		pre.setEstado("Atrasado");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.parse("2024-06-01", formatter));
		fechaFin = LocalDate.parse("2024-06-30", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("Moby Dick");
		pre.setUsuarioEmail("usuario6@example.com");
		pre.setEstado("Disponible");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());
		fechaFin = LocalDate.parse("2024-08-15", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("Frankenstein");
		pre.setUsuarioEmail("usuario7@example.com");
		pre.setEstado("Reservado");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.parse("2024-07-01", formatter));
		fechaFin = LocalDate.parse("2024-07-31", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("The Poetry of Robert Frost");
		pre.setUsuarioEmail("usuario8@example.com");
		pre.setEstado("Atrasado");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.now());
		fechaFin = LocalDate.parse("2024-09-01", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("Pride and Prejudice");
		pre.setUsuarioEmail("usuario9@example.com");
		pre.setEstado("Disponible");
		gp.insert(pre);

		pre = new Prestamo();

		pre.setFechaInicio(LocalDate.parse("2024-06-15", formatter));
		fechaFin = LocalDate.parse("2024-07-15", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("How to Win Friends and Influence People");
		pre.setUsuarioEmail("usuario10@example.com");
		pre.setEstado("Reservado");
		gp.insert(pre);

		
	}
	
}
