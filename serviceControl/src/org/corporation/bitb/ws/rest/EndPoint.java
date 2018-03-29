package org.corporation.bitb.ws.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.corporation.bitb.logic.Facade;
import com.google.gson.Gson;

@Path("/Servicios")

public class EndPoint {
	Facade facade =new Facade();
		
	@GET
	@Path("/registrar/{identificacion}")
	@Produces(MediaType.TEXT_PLAIN)
	public String registrar(@PathParam("identificacion")String identificacion){						
		return facade.registrar(identificacion);
	}
	
	@GET
	@Path("/registrar/{identificacion}/{observacion}")
	@Produces(MediaType.TEXT_PLAIN)
	public String registrar(@PathParam("identificacion")String identificacion,@PathParam("observacion")String observacion){						
		return facade.registrar(identificacion,observacion);
	}
	
	@GET
	@Path("/persona")
	@Produces(MediaType.APPLICATION_JSON)
	public String consultarPersonas(){						
		return new Gson().toJson(facade.consultarPersona());
	}
	
	@POST
    @Path("/persona")
    @Consumes(MediaType.APPLICATION_JSON)
    public String crearPersona(String data) { 
        String result = "Data post: "+data;
        return facade.insertarPersona(data); 
    }
	
	@GET
	@Path("/registro/{persona}/{fechaDesde}/{fechaHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public String consultarRegistros(@PathParam("persona")String persona, @PathParam("fechaDesde")String fechaDesde, @PathParam("fechaHasta")String fechaHasta){
		return new Gson().toJson(facade.consultarRegistro(persona, fechaDesde, fechaHasta));
	}
	
	@GET
	@Path("/consultar/{sql}")
	@Produces(MediaType.APPLICATION_JSON)
	public String consultar(@PathParam("sql")String sql){						
		return new Gson().toJson(facade.consultar(sql));
	}
		
}
