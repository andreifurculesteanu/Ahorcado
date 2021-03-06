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
		
		
		if (request.getParameter("newgame") == null) { //viene de primera vez por el doGet()
			// se crea una sesi�n asociada a la petici�n
			HttpSession laSesion = request.getSession(true);
			
			// genero la palabra
			String palabra = Utilidades.generaPalabra();
			String palabraSin = Utilidades.limpiar(palabra);
			String fallosDisponibles = "7";
			String letrasConGuiones[] = new String[palabra.length()]; // array de letras pero con guiones
			String letrasProbadas = "";
			String ultimaRecibida = "";
			String[] letras = palabraSin.split("");
			int jugando = 1;
			int ganado = 0;
			int perdido = 0;
			int letraMal = 0;

			for (int i = 0; i < letrasConGuiones.length; i++) {
				letrasConGuiones[i] = "_";
			}

			// meto en la sesion: palabra, fallos dispopnibles y array de letras de la
			// palabra
			laSesion.setAttribute("palabra", palabra);
			laSesion.setAttribute("palabraSin", palabraSin);
			laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
			laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
			laSesion.setAttribute("letrasProbadas", letrasProbadas);
			laSesion.setAttribute("ultimaRecibida", ultimaRecibida);
			laSesion.setAttribute("letras", letras);
			laSesion.setAttribute("jugando", jugando);
			laSesion.setAttribute("ganado", ganado);
			laSesion.setAttribute("perdido", perdido);
			laSesion.setAttribute("letraMal", letraMal);
			
		} else {
			//viene de "volver a jugar"
			
			// se vac�a la sesi�n si existe
            if (request.getSession(false) != null) {  // si existe una sesi�n
                request.getSession().invalidate();  // ... la elimina
            }
			
            
            // se crea una sesi�n asociada a la petici�n
         	HttpSession laSesion = request.getSession(true);
            
            // genero la palabra
    		String palabra = Utilidades.generaPalabra();
    		String palabraSin = Utilidades.limpiar(palabra);
    		String fallosDisponibles = "7";
    		String letrasConGuiones[] = new String[palabra.length()]; // array de letras pero con guiones
    		String letrasProbadas = "";
    		String ultimaRecibida = "";
    		String[] letras = palabraSin.split("");
    		int jugando = 1;
    		int ganado = 0;
    		int perdido = 0;
    		int letraMal = 0;

    		for (int i = 0; i < letrasConGuiones.length; i++) {
    			letrasConGuiones[i] = "_";
    		}

    		// meto en la sesion: palabra, fallos dispopnibles y array de letras de la
    		// palabra
    		laSesion.setAttribute("palabra", palabra);
    		laSesion.setAttribute("palabraSin", palabraSin);
    		laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
    		laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
    		laSesion.setAttribute("letrasProbadas", letrasProbadas);
    		laSesion.setAttribute("ultimaRecibida", ultimaRecibida);
    		laSesion.setAttribute("letras", letras);
    		laSesion.setAttribute("jugando", jugando);
    		laSesion.setAttribute("ganado", ganado);
    		laSesion.setAttribute("perdido", perdido);
    		laSesion.setAttribute("letraMal", letraMal);
            
			
		}
		
		

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
				
				if (Utilidades.letraOK(ultimaRecibida) && Utilidades.compruebaTamano(ultimaRecibida)) {
					/* La letra esta bien, asi que podemos recuperar la sesion y ver si la palabra contiene la letra o no*/
					
					// Lo primero es recuperar la sesion
					HttpSession laSesion = request.getSession(true);

					// Se recuperan las variables guardadas en la sesion
					String palabra = (String) laSesion.getAttribute("palabra");
					String palabraSin = (String) laSesion.getAttribute("palabraSin");
					String fallosDisponibles = (String) laSesion.getAttribute("fallosDisponibles");
					String[] letras = (String[]) laSesion.getAttribute("letras");
					String letrasProbadas = (String) laSesion.getAttribute("letrasProbadas");
					String[] letrasConGuiones = (String[]) laSesion.getAttribute("letrasConGuiones");
					int jugando = (int) laSesion.getAttribute("jugando");
					int ganado = (int) laSesion.getAttribute("ganado");
					int perdido = (int) laSesion.getAttribute("perdido");
					int letraMal = (int) laSesion.getAttribute("letraMal");
					
					
					letraMal = 0;
					
					
					//se a�ade la letra tambien a las letras ya usadas
					
					if(letrasProbadas.length()==0) {
						letrasProbadas = ultimaRecibida;
					}else {
						letrasProbadas = letrasProbadas + "-" + ultimaRecibida;
					}
					
					
					//si la contiene cambia en la palabra oculta el guion por la letra en concreto
					if (!Utilidades.contieneLetra(ultimaRecibida, palabraSin)) {
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
								letrasConGuiones[i] = Character.toString(palabra.charAt(i));
							}
						}
					}
					
					//Cambio estado de juego para saber si se esta jugando, se ha ganado, o perdido
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
					laSesion.setAttribute("palabraSin", palabraSin);
					laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
					laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
					laSesion.setAttribute("letrasProbadas", letrasProbadas);
					laSesion.setAttribute("ultimaRecibida", ultimaRecibida);
					laSesion.setAttribute("jugando", jugando);
					laSesion.setAttribute("ganado", ganado);
					laSesion.setAttribute("perdido", perdido);
					laSesion.setAttribute("letraMal", letraMal);
					
				} else {
					//falta poner que pasa cuando no introduzco bien la letra
					// falta poner cuando la letra ya esta introducida
					
					// Lo primero es recuperar la sesion
					HttpSession laSesion = request.getSession(true);

					// Se recuperan las variables guardadas en la sesion
					String palabra = (String) laSesion.getAttribute("palabra");
					String palabraSin = (String) laSesion.getAttribute("palabraSin");
					String fallosDisponibles = (String) laSesion.getAttribute("fallosDisponibles");
					String[] letras = (String[]) laSesion.getAttribute("letras");
					String letrasProbadas = (String) laSesion.getAttribute("letrasProbadas");
					String[] letrasConGuiones = (String[]) laSesion.getAttribute("letrasConGuiones");
					int jugando = (int) laSesion.getAttribute("jugando");
					int ganado = (int) laSesion.getAttribute("ganado");
					int perdido = (int) laSesion.getAttribute("perdido");
					int letraMal = (int) laSesion.getAttribute("letraMal");
					
					
					letraMal = 1;
					
					//vuelvo a pasar todo a la sesion
					laSesion.setAttribute("palabra", palabra);
					laSesion.setAttribute("palabraSin", palabraSin);
					laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
					laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
					laSesion.setAttribute("letrasProbadas", letrasProbadas);
					laSesion.setAttribute("ultimaRecibida", ultimaRecibida);
					laSesion.setAttribute("jugando", jugando);
					laSesion.setAttribute("ganado", ganado);
					laSesion.setAttribute("perdido", perdido);
					laSesion.setAttribute("letraMal", letraMal);
					
				}

				/*
				 * Las 3 siguientes lineas delegan al JSP pintar el formulario (segun el doPost)
				 */
				String vista = "/juego.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
				dispatcher.forward(request, response);
		
	}

}