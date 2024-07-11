package ec.edu.ups.ppw.ProyectoFinal.Services;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Libro;
import ec.edu.ups.ppw.ProyectoFinal.bussines.GestionLibros;
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

@Path("/libros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibroService {
	
	@Inject
    private GestionLibros gLibros;

    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Libro libro) {
        try {
            gLibros.createLibro(libro);
            return Response.status(201).entity(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
        }
    }

    @PUT
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Libro libro) {
        try {
            gLibros.updateLibro(libro);
            return Response.ok(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("nombre") String nombre) {
        try {
            gLibros.deleteLibro(nombre);
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Todo bien")).build();
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("Nombre incorrecto")) {
                return Response.status(400).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
            } else if (e.getMessage().equals("Libro no existe")) {
                return Response.status(404).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
            } else {
                return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
            }
        }
    }

    @GET
    @Path("{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("nombre") String nombre) {
        Libro libro;
        try {
            libro = gLibros.getLibro(nombre);
            return Response.ok(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Libro> libros = gLibros.getLibros();
            return Response.ok(libros).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }
	
	
}
