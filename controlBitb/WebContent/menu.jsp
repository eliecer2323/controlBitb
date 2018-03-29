<div class="overlay"></div>

<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper"
	role="navigation">
	<ul class="nav sidebar-nav">
		<li class="sidebar-brand"><a href="<%=request.getContextPath()%>/index.jsp"> Bitb Corporation </a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">Reportes <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header"></li>
				<li><a href="<%=request.getContextPath()%>/reporte/registro.jsp">Reporte de registros</a></li>
				<li><a href="<%=request.getContextPath()%>/reporte/actividad.jsp">Reporte de actvidad</a></li>
				<li><a href="#">Reporte de pagos</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">Actividades <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header"></li>
				<li><a href="#">Asignar</a></li>
				<li><a href="#">Editar</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">Empleados <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header"></li>
				<li><a href="<%=request.getContextPath()%>/empleado/editar.jsp">Editar</a></li>
				<li><a href="#">Salario</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header"></li>
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
			</ul></li>
	</ul>
</nav>