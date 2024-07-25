package ec.edu.ups.ppw.ProyectoFinal.Services;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinal.bussines.GestionPrestamos;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/prestamos")
public class PrestamoService {
	
	@Inject
	private GestionPrestamos gp;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Prestamo prestamo) {
		try {
			gp.crear(prestamo);
			return Response.ok(prestamo).build();
		} catch (Exception e) {
			message error = new message(1, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(){
		List<Prestamo> prestamos = gp.getAll();
		if(prestamos.size()>0) {
			return Response.ok(prestamos).build();
		}else {
			message em = new message(10, "No se registran libros");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}

	@GET
	@Path("{usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listUsuario(@PathParam("usuario") String usuario){
		List<Prestamo> prestamos = gp.getUsuario(usuario);
		if(prestamos.size()>0) {
			return Response.ok(prestamos).build();
		}else {
			message em = new message(10, "No se registran libros");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}

}
