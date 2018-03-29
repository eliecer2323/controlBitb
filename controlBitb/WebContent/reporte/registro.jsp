<%@page import="org.corporation.bitb.servicio.Consulta"%>
<%@page import="org.corporation.bitb.vo.Persona"%>
<%@page import="org.corporation.bitb.vo.Registro"%>
<%@page import="java.util.List"%>
<%
String persona = null;
String fechaDesde = null;
String fechaHasta = null;
if(request.getParameter("fecha_desde")!=null && !request.getParameter("fecha_desde").isEmpty()){
	fechaDesde = request.getParameter("fecha_desde");
}
if(request.getParameter("fecha_hasta")!=null && !request.getParameter("fecha_hasta").isEmpty()){
	fechaHasta = request.getParameter("fecha_hasta");
}
if(request.getParameter("persona")!=null && !request.getParameter("persona").isEmpty()){
	persona = request.getParameter("persona");
}
Consulta consulta = new Consulta();
List<Persona> personas = consulta.traerPersonas();
List<Registro> registros = consulta.traerRegistros(persona, fechaDesde, fechaHasta);
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<%=request.getContextPath()%>/recursos/estilos/bootstrap.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/recursos/estilos/comun.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/comun.js"></script>
	<title>Reporte de registros</title>
</head>
<body>
	<div id="wrapper">
        <%@include file="/menu.jsp" %>
        
        <div id="page-content-wrapper">
            <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                <span class="hamb-top"></span>
    			<span class="hamb-middle"></span>
				<span class="hamb-bottom"></span>
            </button>
            <div class="container">
            	<div class="row">
				    <div class="col-lg-8 col-lg-offset-2">
				    	<form class="form-inline" action="registro.jsp" method="post">
							<div class="form-group">
								<!-- label for="persona">Persona:</label -->
								<select class="form-control" id="persona" name="persona">
									<option value="null">Seleccione Persona</option>
									<%
									for(Persona p : personas){
										out.println("<option value=\""+p.getIdPersona()+"\">"+p.getNombres()+" "+p.getApellidos()+"</option>");
									}
									%>
						    	</select>
							</div>
							<div class="form-group">
								<!-- label for="fecha_desde">fecha desde:</label -->
								<input type="date" class="form-control" id="fecha_desde" name="fecha_desde">
							</div>
							<div class="form-group">
								<label for="fecha_hasta"> - </label>
								<input type="date" class="form-control" id="fecha_hasta" name="fecha_hasta">
							</div>
							<button type="submit" class="btn btn-default">Consultar</button>
						</form>
						<br>
						<table class="table table-bordered">
						    <thead>
						    	<tr>
							        <th>Persona</th>
							        <th>Fecha</th>
							        <th>Hora Inicio</th>
							        <th>Hora Fin</th>
							        <th>Horas</th>
							        <th>Observacion</th>
							        <th>Valor</th>
							    </tr>
						    </thead>
						    <tbody>
						    	<%
						    	for(Registro r : registros){
						    		out.println("<tr>");
						    		out.println("<td>"+r.getPersona()+"</td>");
						    		out.println("<td>"+r.getFecha()+"</td>");
						    		out.println("<td>"+r.getHoraInicio()+"</td>");
						    		out.println("<td>"+r.getHoraFin()+"</td>");
						    		out.println("<td>"+r.getHoras()+"</td>");
						    		out.println("<td>"+r.getObservacion()+"</td>"); 
						    		out.println("<td>"+r.getValor()+"</td>");
							        out.println("</tr>");
						    	}
						    	%>
							</tbody>
						</table>
		        	</div>
		    	</div>
            </div>
        </div>

    </div>
</body>
</html>