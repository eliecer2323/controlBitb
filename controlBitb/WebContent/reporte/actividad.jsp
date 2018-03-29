<%@page import="org.corporation.bitb.servicio.Consulta"%>
<%@page import="org.corporation.bitb.vo.Persona"%>
<%@page import="org.corporation.bitb.vo.Registro"%>
<%@page import="java.util.List"%>
<%

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
	<title>Reporte de actividad</title>
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
				    
				   	</div>
		    	</div>
            </div>
        </div>

    </div>
</body>
</html>