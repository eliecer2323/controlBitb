<%@page import="org.corporation.bitb.servicio.Check"%>
<%
String mensaje = null;

if(request.getParameter("identificacion")!=null){
	Check registro = new Check();
	if(request.getParameter("observacion")!=null){
		mensaje = registro.deslogear(request.getParameter("identificacion"),request.getParameter("observacion"));
	}else{
		mensaje = registro.logear(request.getParameter("identificacion"));
	}
}
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
	<title>Control Bitb Corporation</title>
</head>
<body>
	<div id="wrapper">
        <%@include file="menu.jsp" %>
        
        <div id="page-content-wrapper">
            <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                <span class="hamb-top"></span>
    			<span class="hamb-middle"></span>
				<span class="hamb-bottom"></span>
            </button>
            <div class="container">
            	<div class="row">
				    <div class="col-lg-8 col-lg-offset-2">
				    	<%
				    	if(mensaje!=null){ 
				    		if(mensaje.endsWith("observación")){
					    		out.println("<form method=\"post\" action=\"index.jsp\">");
					    		out.println("<div class=\"alert alert-success alert-dismissable\">");
					    		out.println("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
					    		out.println("<label for=\"observacion\">"+mensaje+"</label>");
					    		out.println("<textarea class=\"form-control\" rows=\"5\" id=\"observacion\" name=\"observacion\"></textarea>");
					    		out.println("<input type=\"hidden\" id=\"identificacion\" name=\"identificacion\" value=\""+request.getParameter("identificacion")+"\">");
					    		out.println("<input type=\"submit\" class=\"btn btn-success\" value=\"Completar\">");
					    		out.println("</div>");
					    		out.println("</form>");
					    	}else{
					    		out.println("<div class=\"alert alert-success alert-dismissable\">");
					    		out.println("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
					    		out.println(mensaje);
					    		out.println("</div>");
					    	}
				    	}
				    	%>
				    	<form method="post" action="index.jsp">
					    	<div class="form-group">
								<label for="usr">Identificación:</label>
								<input type="number" class="form-control" id="identificacion" name="identificacion" required>
			  				</div>
			          		<input type="submit" class="btn btn-primary" value="Registrar">
			          	</form>
		        	</div>
		    	</div>
            </div>
        </div>

    </div>
</body>
</html>