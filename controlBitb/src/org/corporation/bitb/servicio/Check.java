package org.corporation.bitb.servicio;

public class Check {
	public String logear(String indentificacion){
		String respuesta = WebService.obtenerTexto("registrar", indentificacion);
		return respuesta;
	}
	
	public String deslogear(String indentificacion, String observacion){
		String respuesta = WebService.obtenerTexto("registrar", indentificacion+"/"+observacion);
		return respuesta;
	}
}
