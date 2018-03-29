package org.corporation.bitb.servicio;

import javax.ws.rs.core.MediaType;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class WebService {
	
	private static String hostWebService = "http://localhost:8080/serviceControl/rest/Servicios/";
	
	public static String obtenerTexto(String accion, String parametros){
		Client client = Client.create();
        WebResource resource = client.resource(hostWebService+accion+"/");
 
        System.out.println("peticion: "+hostWebService+accion+"/"+parametros);
        String respuesta = resource.path(parametros).accept(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println("respuesta: "+respuesta);
		return respuesta;
	}
	
	public static JsonElement obtenerJson(String accion, String parametros){
		Client client = Client.create();
        WebResource resource = client.resource(hostWebService+accion+"/");
        
        System.out.println("peticion: "+hostWebService+accion+"/"+parametros);
        String respuesta = resource.path(parametros).accept(MediaType.APPLICATION_JSON).get(String.class);
        JsonElement jelement = new JsonParser().parse(respuesta);
        System.out.println("respuesta: "+respuesta);
        return jelement;
	}
	
	public static String insertarJson(String accion, String jsonElement){
		 try {
			Client client = Client.create();
			WebResource resource = client.resource(hostWebService+accion+"/");
			System.out.println("post: "+hostWebService+accion+"/"+jsonElement);
			String respuesta = resource.type(MediaType.APPLICATION_JSON).post(String.class, jsonElement);
			System.out.println("respuesta: "+respuesta);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "lol";
	}
}
