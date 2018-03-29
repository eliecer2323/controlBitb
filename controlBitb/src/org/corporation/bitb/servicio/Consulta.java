package org.corporation.bitb.servicio;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.corporation.bitb.vo.Persona;
import org.corporation.bitb.vo.Registro;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class Consulta {
	public List<Persona> traerPersonas(){
		try {
			List<Persona> personas = new ArrayList<Persona>();
			JsonElement jelement = WebService.obtenerJson("persona", "");
			JsonArray jarray = jelement.getAsJsonArray();
			SimpleDateFormat formato = new SimpleDateFormat("MMM dd, yyyy");
			for(JsonElement je : jarray){
				Persona p = new Persona();
				p.setIdPersona(je.getAsJsonObject().get("idPersona").getAsInt());
				p.setIdTipoDocumento(je.getAsJsonObject().get("idTipoDocumento").getAsInt());
				p.setTipoPersona(je.getAsJsonObject().get("tipoPersona").getAsString());
				p.setNumeroDocumento(je.getAsJsonObject().get("numeroDocumento").getAsInt());
				if(je.getAsJsonObject().get("fechaNacimiento")!=null){
					Date fechaNacimiento = formato.parse(je.getAsJsonObject().get("fechaNacimiento").getAsString());
					java.sql.Date sqlFecha = new java.sql.Date(fechaNacimiento.getTime());
					p.setFechaNacimiento(sqlFecha);
				}
				p.setNombres(je.getAsJsonObject().get("nombres").getAsString());
				p.setApellidos(je.getAsJsonObject().get("apellidos").getAsString());
				p.setRazonSocial(je.getAsJsonObject().get("razonSocial").getAsString());
				p.setTelefonosCelulares(je.getAsJsonObject().get("telefonosCelulares").getAsString());
				p.setCorreoElectronico(je.getAsJsonObject().get("correoElectronico").getAsString());
				p.setGenero(je.getAsJsonObject().get("genero").getAsString());
				personas.add(p);
			}
			return personas;
		}catch (ParseException ex){
			return null;
		}
	}
	
	public List<Registro> traerRegistros(String persona, String fechaDesde, String fechaHasta){
		try {
			List<Registro> registros = new ArrayList<Registro>();
			JsonElement jelement = WebService.obtenerJson("registro", persona+"/"+fechaDesde+"/"+fechaHasta);
			JsonArray jarray = jelement.getAsJsonArray();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM dd, yyyy");
			SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a");
			Long ms;
			Time hora;
			for(JsonElement je : jarray){
				Registro r = new Registro();
				r.setIdRegistro(je.getAsJsonObject().get("idRegistro").getAsInt());
				r.setMedio(je.getAsJsonObject().get("medio").getAsString());
				r.setPersona(je.getAsJsonObject().get("persona").getAsString());
				Date fecha = formatoFecha.parse(je.getAsJsonObject().get("fecha").getAsString());
				java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
				r.setFecha(sqlFecha);
				ms = formatoHora.parse(je.getAsJsonObject().get("horaInicio").getAsString()).getTime();
				hora = new Time(ms);
				r.setHoraInicio(hora);
				if(je.getAsJsonObject().get("horaFin")!=null){
					ms = formatoHora.parse(je.getAsJsonObject().get("horaFin").getAsString()).getTime();
					hora = new Time(ms);
					r.setHoraFin(hora);
					r.setHoras(je.getAsJsonObject().get("horas").getAsString());
					r.setValor(je.getAsJsonObject().get("valor").getAsInt());
				}
				if(je.getAsJsonObject().get("observacion")!=null){
					r.setObservacion(je.getAsJsonObject().get("observacion").getAsString());
				}
				registros.add(r);
			}
			return registros;
		}catch (ParseException ex){
			return null;
		}
	}
	
}
