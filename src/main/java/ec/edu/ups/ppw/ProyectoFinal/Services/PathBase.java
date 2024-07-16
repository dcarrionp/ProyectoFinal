package ec.edu.ups.ppw.ProyectoFinal.Services;

import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.ppw.ProyectoFinal.bussines.GestionCategorias;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("rs")
public class PathBase extends Application{
	@Override
	public Set<Class<?>> getClasses(){
		Set <Class<?>> resources=new HashSet<>();
		resources.add(CORSFilter.class);
		resources.add(LibroService.class);
		resources.add(GestionCategorias.class);
		return resources;
	}
}
