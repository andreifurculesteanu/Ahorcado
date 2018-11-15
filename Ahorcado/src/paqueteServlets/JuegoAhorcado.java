package paqueteServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paqueteUtilidades.Utilidades;

/**
 * Servlet implementation class JuegoAhorcado
 */
@WebServlet("/JuegoAhorcado")
public class JuegoAhorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JuegoAhorcado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// se crea una sesión asociada a la petición
		HttpSession laSesion = request.getSession(true);

		// genero la palabra
		String palabra = Utilidades.generaPalabra();
		String fallosDisponibles = "7";
		String letrasConGuiones[] = new String[palabra.length()]; // array de letras pero con guiones
		String letrasProbadas = "";
		String ultimaRecibida = "";
		String[] letras = palabra.split("");
		int jugando = 1;
		int ganado = 0;
		int perdido = 0;

		for (int i = 0; i < letrasConGuiones.length; i++) {
			letrasConGuiones[i] = "_";
		}

		// meto en la sesion: palabra, fallos dispopnibles y array de letras de la
		// palabra
		laSesion.setAttribute("palabra", palabra);
		laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
		laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
		laSesion.setAttribute("letrasProbadas", letrasProbadas);
		laSesion.setAttribute("ultimaRecibida", ultimaRecibida);
		laSesion.setAttribute("letras", letras);
		laSesion.setAttribute("jugando", jugando);
		laSesion.setAttribute("ganado", ganado);
		laSesion.setAttribute("perdido", perdido);

		/*
		 * Las 3 siguientes lineas delegan al JSP pintar el formulario (segun el doGet)
		 */
		String vista = "/juego.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// se recupera la ultima letra recibida
		String ultimaRecibida = Utilidades.limpiar(request.getParameter("letraIntroducida").toLowerCase());
		
		if (Utilidades.letraOK(ultimaRecibida) == true) {
			/* La letra esta bien, asi que podemos recuperar la sesion y ver si la palabra contiene la letra o no*/
			
			// Lo primero es recuperar la sesion
			HttpSession laSesion = request.getSession(true);

			// Se recuperan las variables guardadas en la sesion
			String palabra = (String) laSesion.getAttribute("palabra");
			String fallosDisponibles = (String) laSesion.getAttribute("fallosDisponibles");
			String[] letras = (String[]) laSesion.getAttribute("letras");
			String letrasProbadas = (String) laSesion.getAttribute("letrasProbadas");
			String[] letrasConGuiones = (String[]) laSesion.getAttribute("letrasConGuiones");
			int jugando = (int) laSesion.getAttribute("jugando");
			int ganado = (int) laSesion.getAttribute("ganado");
			int perdido = (int) laSesion.getAttribute("perdido");
			
			
			//se añade la letra tambien a las letras ya usadas
			
			if(letrasProbadas.length()==0) {
				letrasProbadas = ultimaRecibida;
			}else {
				letrasProbadas = letrasProbadas + "-" + ultimaRecibida;
			}
			
			
			//si la contiene cambia en la palabra oculta el guion por la letra en concreto
			if (!Utilidades.contieneLetra(ultimaRecibida, palabra)) {
				//cambio los fallosDisponibles de String a int
				int fallosDispo = Integer.parseInt(fallosDisponibles);
				//si no la contiene resta vidas
				fallosDispo = fallosDispo - 1;
				//vuelvo a convertir los fallos a String
				fallosDisponibles = "" + fallosDispo; 
			} else {
				//for que recorre la palabra y busca si contiene la letra
				for (int i = 0; i < letrasConGuiones.length; i++) {
					if(letras[i].equals(ultimaRecibida)) {
						System.out.println(letrasConGuiones[i]+"g");
						System.out.println(palabra.charAt(i)+"p");
						letrasConGuiones[i] = Character.toString(palabra.charAt(i));
					}
				}
			}
			
			
			if (Integer.parseInt(fallosDisponibles) == 0) {
				jugando = 0;
				ganado = 0;
				perdido = 1;
			} else if(Integer.parseInt(fallosDisponibles) > 0 && Utilidades.coincidencia(letrasConGuiones, palabra)) {
				jugando = 0;
				ganado = 1;
				perdido = 0;
			}
			
			//vuelvo a pasar todo a la sesion
			laSesion.setAttribute("palabra", palabra);
			laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
			laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
			laSesion.setAttribute("letrasProbadas", letrasProbadas);
			laSesion.setAttribute("ultimaRecibida", ultimaRecibida);
			laSesion.setAttribute("jugando", jugando);
			laSesion.setAttribute("ganado", ganado);
			laSesion.setAttribute("perdido", perdido);
			
		} else {
			
		}

		/*
		 * Las 3 siguientes lineas delegan al JSP pintar el formulario (segun el doGet)
		 */
		String vista = "/juego.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response);

	}

}