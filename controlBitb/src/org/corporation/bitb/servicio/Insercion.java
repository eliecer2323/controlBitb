package org.corporation.bitb.servicio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.corporation.bitb.vo.Persona;

import com.google.gson.Gson;

public class Insercion {
	public String insertarEmpleado(Map<String, String[]> parametros){
		try {
			Persona persona = new Persona();
			persona.setIdTipoDocumento(Integer.parseInt(parametros.get("tipo_documento")[0]));
			persona.setNumeroDocumento(Integer.parseInt(parametros.get("numero_documento")[0]));
			persona.setNombres(parametros.get("nombres")[0]);
			persona.setApellidos(parametros.get("apellidos")[0]);
			persona.setCorreoElectronico(parametros.get("correo_electronico")[0]);
			persona.setGenero(parametros.get("genero")[0]);
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaNacimiento = formato.parse(parametros.get("fecha_nacimiento")[0]);
			java.sql.Date sqlFecha = new java.sql.Date(fechaNacimiento.getTime());
			persona.setFechaNacimiento(sqlFecha);
			String je = new Gson().toJson(persona);
			String mensaje = WebService.insertarJson("persona",je);
			return mensaje;
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		}
	}
}
