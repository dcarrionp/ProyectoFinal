package ec.edu.ups.ppw.ProyectoFinal.bussines;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itextpdf.layout.element.Table;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

import ec.edu.ups.ppw.ProyectoFinal.DAO.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinal.Model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPrestamos {
	
	@Inject
	PrestamoDAO dao;
	
	public void crear(Prestamo us) {
		dao.insert(us);
	}

	public Prestamo read(String correo) {
		return dao.read(correo);
	}

	public void update(Prestamo us) {
		dao.update(us);
	}

	public void delete(String correo) {
		dao.delete(correo);
	}

	public List<Prestamo> getAll(){
		return dao.getAll();
	}
	
	public List<Prestamo> getUsuario(String usuario){
		return dao.getUsuario(usuario);
	}

	public List<Prestamo> getLibro(String libro){
		return dao.getPrestamosByNombreLibro(libro);
	}
	
	public byte[] generarReporteHistorialPrestamosPDF() {
		ByteArrayOutputStream
		baos = new ByteArrayOutputStream();

	    try {
	        PdfWriter writer = new PdfWriter(baos);
	        PdfDocument pdfDoc = new PdfDocument(writer);
	        Document document = new Document(pdfDoc);

	        List<Usuario> usuarios = dao.getUsuariosConPrestamos();

	        for (Usuario user : usuarios) {
	            List<Prestamo> prestamos = dao.getPrestamosPorUsuario(user);

	            long totalReservas = prestamos.stream().count();
	            long totalPrestados = prestamos.stream().filter(p -> p.getEstado().equals("Prestado")).count();
	            long totalAtrasados = prestamos.stream().filter(p -> p.getEstado().equals("Atrasado")).count();
	            long totalDevueltos = prestamos.stream().filter(p -> p.getEstado().equals("Devuelto")).count();

	            document.add(new Paragraph("Usuario: " + user.getUsuario()));
	            document.add(new Paragraph("Total Reservas: " + totalReservas));
	            document.add(new Paragraph("Libros Prestados: " + totalPrestados));
	            document.add(new Paragraph("Libros Atrasados: " + totalAtrasados));
	            document.add(new Paragraph("Libros Devueltos: " + totalDevueltos));

	            Table table = new Table(4);
	            table.addCell(new Cell().add(new Paragraph("Libro")));
	            table.addCell(new Cell().add(new Paragraph("Fecha Inicio")));
	            table.addCell(new Cell().add(new Paragraph("Fecha Fin")));
	            table.addCell(new Cell().add(new Paragraph("Estado")));

	            for (Prestamo prestamo : prestamos) {
	                table.addCell(new Cell().add(new Paragraph(prestamo.getLibro().getNombre())));
	                table.addCell(new Cell().add(new Paragraph(prestamo.getFechaInicio().toString())));
	                table.addCell(new Cell().add(new Paragraph(prestamo.getFechaFin().toString())));
	                table.addCell(new Cell().add(new Paragraph(prestamo.getEstado())));
	            }

	            document.add(table);
	            document.add(new Paragraph("\n"));
	        }

	        List<Libro> librosReservados = dao.getAll().stream()
	                .filter(p -> p.getEstado().equals("Reservado"))
	                .map(Prestamo::getLibro)
	                .collect(Collectors.groupingBy(Libro::getNombre, Collectors.counting()))
	                .entrySet().stream()
	                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
	                .limit(5)
	                .map(Map.Entry::getKey)
	                .map(dao::getLibroByName)
	                .collect(Collectors.toList());

	            document.add(new Paragraph("Top 5 Libros Más Reservados:"));

	            Table topLibrosTable = new Table(2);
	            topLibrosTable.addHeaderCell(new Cell().add(new Paragraph("Libro")));
	            topLibrosTable.addHeaderCell(new Cell().add(new Paragraph("Cantidad de Reservas")));

	            for (Libro libro : librosReservados) {
	                long count = dao.getCountReservasByLibro(libro.getNombre());
	                topLibrosTable.addCell(new Cell().add(new Paragraph(libro.getNombre())));
	                topLibrosTable.addCell(new Cell().add(new Paragraph(String.valueOf(count))));
	            }

	            document.add(topLibrosTable);

	        document.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return baos.toByteArray();
    }

	public byte[] generarReporteReservasEntreFechasPDF(LocalDate fechaInicio, LocalDate fechaFin) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    try {
	        PdfWriter writer = new PdfWriter(baos);
	        PdfDocument pdfDoc = new PdfDocument(writer);
	        Document document = new Document(pdfDoc);

	        List<Prestamo> reservas = dao.getReservasEntreFechas(fechaInicio, fechaFin);

	        long totalReservas = reservas.size();
	        long totalPrestados = reservas.stream().filter(p -> p.getEstado().equals("Reservado")).count();
	        long totalAtrasados = reservas.stream().filter(p -> p.getEstado().equals("Atrasado")).count();
	        long totalDevueltos = reservas.stream().filter(p -> p.getEstado().equals("Disponible")).count();

	        document.add(new Paragraph("Reporte de Reservas entre " + fechaInicio + " y " + fechaFin));
	        document.add(new Paragraph("Total Reservas: " + totalReservas));
	        document.add(new Paragraph("Total Prestados: " + totalPrestados));
	        document.add(new Paragraph("Total Atrasados: " + totalAtrasados));
	        document.add(new Paragraph("Total Devueltos: " + totalDevueltos));
	        document.add(new Paragraph("\n"));

	        Table table = new Table(5);
	        table.addHeaderCell(new Cell().add(new Paragraph("Usuario")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Libro")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Fecha Inicio")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Fecha Fin")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Estado")));

	        for (Prestamo prestamo : reservas) {
	            table.addCell(new Cell().add(new Paragraph(prestamo.getUsuario().getUsuario())));
	            table.addCell(new Cell().add(new Paragraph(prestamo.getLibro().getNombre())));
	            table.addCell(new Cell().add(new Paragraph(prestamo.getFechaInicio().toString())));
	            table.addCell(new Cell().add(new Paragraph(prestamo.getFechaFin().toString())));
	            table.addCell(new Cell().add(new Paragraph(prestamo.getEstado())));
	        }

	        document.add(table);
	        document.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return baos.toByteArray();
	}
	
}
