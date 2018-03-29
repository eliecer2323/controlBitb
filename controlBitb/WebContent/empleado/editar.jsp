<%@page import="org.corporation.bitb.servicio.Consulta"%>
<%@page import="org.corporation.bitb.servicio.Insercion"%>
<%@page import="org.corporation.bitb.vo.Persona"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%
if(request.getParameter("accion")!=null){
	Insercion insercion = new Insercion();
	insercion.insertarEmpleado(request.getParameterMap()); 
}

Consulta consulta = new Consulta();
List<Persona> personas = consulta.traerPersonas();
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
	<title>Editar empleados</title>
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
						<form class="" action="editar.jsp" method="post">
							<input type="hidden" id="accion" name="accion" value="inscribir">
					    	<table class="table table-bordered">
							    <thead>
							    	<tr>
								        <th>Tipo Doc.</th>
								        <th>Número Documento</th>
								        <th>Fecha Nacimiento</th>
								        <th>Nombres</th>
								        <th>Apellidos</th>
								        <th>Correo Electronico</th>
								        <th>Genero</th>
								        <th>Editar</th>
								    </tr>
							    </thead>
							    <tbody>
							    	<tr>
							    		<td>
											<select class="form-control" id="tipo_documento" name="tipo_documento" required>
												<option value="1">CC</option>
											</select>
										</td>
							    		<td><input type="number" class="form-control" id="numero_documento" name="numero_documento" required></td>
							    		<td><input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" required></td>
							    		<td><input type="text" class="form-control" id="nombres" name="nombres" required></td>
							    		<td><input type="text" class="form-control" id="apellidos" name="apellidos" required></td>
							    		<td><input type="email" class="form-control" id="correo_electronico" name="correo_electronico" required></td>
							    		<td>
											<select class="form-control" id="genero" name="genero" required>
												<option value="M">M</option>
												<option value="F">F</option>
											</select>
										</td>
							    		<td><button type="submit" class="btn btn-default">Inscribir</button></td>
							    	</tr>
							    	<%
							    	for(Persona p : personas){
							    		out.println("<tr>");
							    		out.println("<td>CC</td>");
							    		out.println("<td>"+p.getNumeroDocumento()+"</td>");
							    		out.println("<td>"+p.getFechaNacimiento()+"</td>");
							    		out.println("<td>"+p.getNombres()+"</td>");
							    		out.println("<td>"+p.getApellidos()+"</td>");
							    		out.println("<td>"+p.getCorreoElectronico()+"</td>"); 
							    		out.println("<td>"+p.getGenero()+"</td>");
							    		out.println("<td><a href=\"#\">editar</a></td>");
								        out.println("</tr>");
							    	}
							    	%>
								</tbody>
							</table>
						</form>
				   	</div>
		    	</div>
            </div>
        </div>

    </div>
</body>
</html>