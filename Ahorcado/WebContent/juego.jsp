<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	HttpSession laSesion = request.getSession(true);
	
	String palabra = (String) laSesion.getAttribute("palabra");
	String fallosDisponibles = (String) laSesion.getAttribute("fallosDisponibles");
	String[] letrasConGuiones = (String []) laSesion.getAttribute("letrasConGuiones");
	String[] letras = (String []) laSesion.getAttribute("letras"); 
	String letrasProbadas = (String) laSesion.getAttribute("letrasProbadas");
	String ultimaRecibida = (String) laSesion.getAttribute("ultimaRecibida");
	int jugando = (int) laSesion.getAttribute("jugando");
	int ganado = (int) laSesion.getAttribute("ganado");
	int perdido = (int) laSesion.getAttribute("perdido");
	int letraMal = (int) laSesion.getAttribute("letraMal");
	String ruta = "img/" + fallosDisponibles + ".png";
	String rutaPerdido = "img/" + fallosDisponibles + ".png";
	String rutaGanado = "img/ganado.jpg";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<style>
			#juego{
				float: left;
			}
			
			#fotos{
				float: right;
			}
			
			p{
				color: red;
			}
		</style>
	</head>
	<body>
		<h1 align="center"> Juego ahorcado </h1>
		
		<!-- Div que se muestra mientras estas jugando -->
		<%if (jugando == 1){ %>
		<div align="center">
	 	<div id="juego">
		 	<form method="post" action="JuegoAhorcado">
		 		Palabra a adivinar: <% for (int i = 0; i < letrasConGuiones.length; i++) { %>
		 							 	<%= letrasConGuiones[i]%>
		 							<% } %>
		 		<br/>
		 		<br/>
		 		
		 		<%if (letraMal == 1){ %>
		 			<p> Por favor, introduce una letra correcta! </p>
		 		<% } %>
		 		
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
	 	</div>
	 	<div id="fotos">
	 		<img src="<%= ruta %>">
	 	</div>
	 	</div>
	 	
	 	<% } %>
	 	
	 	<!-- Div que se muestra cuando has ganado -->
	 	<%if (ganado == 1){ %>
	 	<div align="center">
	 		<h1 align="center"> HAS GANADO!!! </h1>
	 		<h3 align="center"> ENHORABUENA!!! </h3>
	 		<br>
	 		Has hacertado la palabra secreta: <%= palabra %>
	 		<br>
	 		<br>
	 		<img src="<%= rutaGanado %>">
	 		<br>
	 		<br>
	 		<a href="JuegoAhorcado?newgame">Juego nuevo</a>
	 	</div>
	 	<% } %>
	 	
	 	<!-- Div que se muestra cuando has perdido -->
	 	<%if (perdido == 1){ %>
	 	<div align="center">
	 		<h1 align="center"> HAS PERDIDO </h1>
	 		<h3 align="center"> LO SIENTO!!! </h3>
	 		
	 		<br>
	 		La palabra secreta era: <%= palabra %>
	 		<br>
	 		<br>
	 		<img src="<%= rutaPerdido %>">
	 		<br>
	 		<br>
	 		<a href="JuegoAhorcado?newgame">Juego nuevo</a>
	 	</div>
	 	<% } %>
	</body>
</html>