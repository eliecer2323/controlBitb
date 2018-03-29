package org.corporation.bitb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.corporation.bitb.conexion.AccessDataBase;
import org.corporation.bitb.conexion.DataBaseEngine;
import org.corporation.bitb.conexion.FactoryAccessDataBase;
import org.corporation.bitb.vo.Persona;
import org.corporation.bitb.vo.Registro;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class GeneralDAO {
	private final Connection connection;
	
	private ResultSet rs;
	private String sql;
	private final AccessDataBase adb;

	public GeneralDAO() {
		adb = FactoryAccessDataBase.getAccessDataBase(DataBaseEngine.POSTGRESQL);
		connection = adb.getConnection();
	}

	public JsonArray consultar(String sql){
    	try {
    		System.out.println("sql: "+sql);
        	JsonArray array = new JsonArray();
			rs=connection.createStatement().executeQuery(sql);
			ResultSetMetaData rsmd=rs.getMetaData();
			JsonArray nombres = new JsonArray();
			for(int i=1; i<=rsmd.getColumnCount(); i++){
				nombres.add(rsmd.getColumnName(i));
			}
			while(rs.next()){
				JsonObject item = new JsonObject();
				for(int i=1; i<=rsmd.getColumnCount(); i++){
					item.addProperty(rsmd.getColumnName(i), rs.getString(i));
				}
				array.add(item);
			}
	    	rs.close();
	    	connection.close();
	    	return array;
    	} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public String registrar(String identificacion, String observacion) {
		try {
			String mensaje = "";
			sql = "select * from control.persona where numero_documento="+identificacion;
			System.out.println("sql: "+sql);
			rs = connection.createStatement().executeQuery(sql);
			if(rs.next()){
				String sql2 = "select current_time hora";
				ResultSet rs2 = connection.createStatement().executeQuery(sql2);
				String hora = "";
				if(rs2.next()){
					hora = rs2.getString("hora");
				}
				rs2.close();
				rs2=null;
				sql2 = "select * from control.registro where id_persona="+rs.getString("id_persona")+" and hora_fin is null";
				rs2 = connection.createStatement().executeQuery(sql2);
				if(rs2.next()){
					if(observacion!=null){
						String sql3 = "update control.registro set hora_fin='"+hora+"', observacion='"+observacion+"' where id_registro="+rs2.getString("id_registro");
						connection.createStatement().executeUpdate(sql3);
						mensaje = "SALIDA "+hora+": "+rs.getString("nombres")+" "+rs.getString("apellidos");
					}else{
						mensaje = rs.getString("nombres")+" "+rs.getString("apellidos")+": ingrese su observación";
					}
				}else{
					String sql3 = "insert into control.registro (medio, id_persona, fecha, hora_inicio) values ('L', 2, current_date, '"+hora+"')";
					connection.createStatement().executeUpdate(sql3);
					mensaje = "INGRESO "+hora+": "+rs.getString("nombres")+" "+rs.getString("apellidos");
				}
				rs2.close();
				rs2=null;
			}else{
				mensaje = "Usuario no encontrado";
			}
			rs.close();
			rs = null;
			connection.close();
			return mensaje;
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public List<Registro> consultarRegistro(String persona, String fechaDesde, String fechaHasta) {
		try {
			List<Registro> registros = new ArrayList<Registro>();
			String filtro = "where 1=1";
			if(!persona.equals("null")){
				filtro += " and r.id_persona="+persona;
			}
			if(!fechaDesde.equals("null")){
				filtro += " and r.fecha>='"+fechaDesde+"'";
			}
			if(!fechaHasta.equals("null")){
				filtro += " and r.fecha<='"+fechaHasta+"'";
			}
			sql = "select r.*,(p.nombres||' '||p.apellidos) persona, (r.hora_fin-r.hora_inicio) horas, s.valor from control.registro r inner join control.persona p using (id_persona) left join control.salario s on (r.fecha>=s.fecha_inicio and r.fecha<=s.fecha_fin) "+filtro+" order by fecha;";
			System.out.println("sqlRegistro: " + sql);
			rs = connection.createStatement().executeQuery(sql);
			SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
			while (rs.next()) {
				Registro r = new Registro();
				r.setIdRegistro(rs.getInt("id_registro"));
				r.setMedio(rs.getString("medio"));
				r.setPersona(rs.getString("persona"));
				r.setFecha(rs.getDate("fecha"));
				r.setHoraInicio(rs.getTime("hora_inicio"));
				r.setHoraFin(rs.getTime("hora_fin"));
				if(rs.getString("horas")!=null){
					double horas = formatoHora.parse(rs.getString("horas")).getHours();
					double minutos = formatoHora.parse(rs.getString("horas")).getMinutes();
					double tiempo = horas+minutos/60;
					double valor = tiempo*rs.getInt("valor");
					r.setHoras(rs.getString("horas"));
					r.setValor((int)valor);
				}
				r.setObservacion(rs.getString("observacion"));
				registros.add(r);
			}
			rs.close();
			rs = null;
			connection.close();
			return registros;
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Persona> consultarPersona() {
		try {
			List<Persona> personas = new ArrayList<Persona>();
			sql = "select * from control.persona order by nombres;";
			System.out.println("sqlPersonas: " + sql);
			rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				Persona p = new Persona();
				p.setIdPersona(rs.getInt("id_persona"));
				p.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
				p.setTipoPersona(rs.getString("tipo_persona")+"");
				p.setNumeroDocumento(rs.getInt("numero_documento"));
				p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				p.setNombres(rs.getString("nombres")+"");
				p.setApellidos(rs.getString("apellidos")+"");
				p.setRazonSocial(rs.getString("razon_social")+"");
				p.setTelefonosCelulares(rs.getString("telefonos_celulares")+"");
				p.setCorreoElectronico(rs.getString("correo_electronico")+"");
				p.setGenero(rs.getString("genero")+"");
				personas.add(p);
			}
			connection.close();
			return personas;
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public String insertarPersona(String datos){
		try {
			JsonElement jelement = new JsonParser().parse(datos);
			JsonObject jDatos = jelement.getAsJsonObject();
			String sql = "insert into control.persona (id_tipo_documento,numero_documento,tipo_persona,fecha_nacimiento,nombres,apellidos,correo_electronico,genero) values ("+jDatos.get("idTipoDocumento").getAsInt()+","+jDatos.get("numeroDocumento").getAsInt()+",'N','"+jDatos.get("fechaNacimiento").getAsString()+"','"+jDatos.get("nombres").getAsString()+"','"+jDatos.get("apellidos").getAsString()+"','"+jDatos.get("correoElectronico").getAsString()+"','"+jDatos.get("genero").getAsString()+"');";
			System.out.println("sqlInsert: "+sql);
			connection.createStatement().executeUpdate(sql);
			return "OK";
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
}
