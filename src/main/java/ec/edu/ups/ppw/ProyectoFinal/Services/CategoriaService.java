package ec.edu.ups.ppw.ProyectoFinal.Services;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Categoria;
import ec.edu.ups.ppw.ProyectoFinal.bussines.GestionCategorias;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/categorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaService {

	@Inject
	private GestionCategorias gc;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Categoria cat) {
		try {
			gc.agregarCategoria(cat);
			return Response.ok(cat).build();
		} catch (Exception e) {
			message error = new message(1, "Ya existe esta categoria");
			return Response.status(Response.Status.CONFLICT)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Categoria cat) {
		try {
			gc.actualizar(cat);
			return Response.ok(cat).build();
		} catch (Exception e) {
			message error = new message(100, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(){
		List<Categoria> categorias = gc.getAll();
		if(categorias.size()>0) {
			return Response.ok(categorias).build();
		}else {
			message em = new message(10, "No se registran categorias");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{nombre}")
	public Response read(@PathParam("nombre") String nombre) {
		Categoria li;
		try {
			li = gc.getCategoria(nombre);
			return Response.ok(li).build();
		} catch (Exception e) {
			message em = new message(11, "No se encuentra la categoria");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@QueryParam("nombre") String ci) {
		try {
			if(gc.getCategoria(ci)==null) {
				throw new Exception("Categoria no encontrada");
			}else {
				gc.eliminar(ci);
				return Response.ok().build();
			}
		} catch (Exception e) {
			message error = new message(101, "Categoria no encontrada");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
}
