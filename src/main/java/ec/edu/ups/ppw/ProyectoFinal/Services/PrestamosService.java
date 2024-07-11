package ec.edu.ups.ppw.ProyectoFinal.Services;

import java.util.List;

import ec.edu.ups.ppw.ProyectoFinal.Model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinal.bussines.GestionPrestamos;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/prestamos")
public class PrestamosService {
	@Inject
    private GestionPrestamos gPrestamos;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarPrestamo(Prestamo prestamo) {
        try {
            gPrestamos.registrarPrestamo(prestamo);
            return Response.status(201).entity(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/devolucion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDevolucion(@PathParam("id") Integer id) {
        try {
            gPrestamos.registrarDevolucion(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPrestamos() {
        try {
            List<Prestamo> prestamos = gPrestamos.listarPrestamos();
            return Response.ok(prestamos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }
}
