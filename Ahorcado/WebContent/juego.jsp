<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String palabra = (String) request.getAttribute("palabra");
	int fallosDisponibles = (Integer) request.getAttribute("fallosDisponibles");
	String[] letrasConGuiones = (String []) request.getAttribute("letrasConGuiones"); 
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
	 		
	 		Introduce una letra: <input type="text" name="letraIntroducida">
	 		<br/>
			<br/>
			<input type="submit" value="Probar letra">
	 	</form>
	</body>
</html>