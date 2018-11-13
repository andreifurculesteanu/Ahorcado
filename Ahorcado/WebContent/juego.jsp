<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	HttpSession laSesion = request.getSession(true);
	
	String palabra = (String) laSesion.getAttribute("palabra");
	String fallosDisponibles = (String) laSesion.getAttribute("fallosDisponibles");
	String[] letrasConGuiones = (String []) laSesion.getAttribute("letrasConGuiones"); 
	String letrasProbadas = (String) laSesion.getAttribute("letrasProbadas");
	String ultimaRecibida = (String) laSesion.getAttribute("ultimaRecibida");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<h1 align="center"> Juego ahorcado </h1>
	 	<form method="post" action="JuegoAhorcado">
	 		Palabra a adivinar: <% for (int i = 0; i < letrasConGuiones.length; i++) { %>
	 							 	<%= letrasConGuiones[i]%>
	 							<% } %>
	 		<br/>
	 		<br/>
	 		Introduce una letra: <input type="text" name="letraIntroducida">
	 		<br/>
			<br/>
			<input type="submit" value="Probar letra">
			<br/>
			<br/>
			Fallos disponibles: <%= fallosDisponibles %>
			<br/>
			Última letra recibida: <%= ultimaRecibida %>
			<br/>
			
			Letras probadas: <%= letrasProbadas %>
	 	</form>
	</body>
</html>