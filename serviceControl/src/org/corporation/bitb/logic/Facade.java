package org.corporation.bitb.logic;

import java.util.List;

import javax.ws.rs.PathParam;

import org.corporation.bitb.dao.GeneralDAO;
import org.corporation.bitb.vo.Persona;
import org.corporation.bitb.vo.Registro;

import com.google.gson.JsonArray;

public class Facade {

	GeneralDAO aDAO = new GeneralDAO();
	
	public String registrar(String identificacion){
		return aDAO.registrar(identificacion, null);
	}
	
	public String registrar(String identificacion, String observacion){
		return aDAO.registrar(identificacion, observacion);
	}
	
	public JsonArray consultar(String sql){
		return aDAO.consultar(sql);
	}
	
	public List<Persona> consultarPersona(){
		return aDAO.consultarPersona();
	}
	
	public List<Registro> consultarRegistro(String persona, String fechaDesde, String fechaHasta){
		return aDAO.consultarRegistro(persona, fechaDesde, fechaHasta); 
	}
	
	public String insertarPersona(String datos){
		return aDAO.insertarPersona(datos);
	}
	
}
