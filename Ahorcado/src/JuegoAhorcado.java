import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    
    //genero la palabra
    String palabra = "";
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// se crea una sesión asociada a la petición
        try {
			
        	
        	HttpSession laSesion = request.getSession(true);
	        
	        //genero la palabra
        	palabra = Utilidades.generaPalabra();
	        int fallosDisponibles = 6;
	        String letrasConGuiones[] = new String[palabra.length()]; //array de letras pero con guiones
	        
	        
	        for (int i = 0; i < letrasConGuiones.length; i++) {
	        	letrasConGuiones[i] = "_";
	        }
	        
	        //se pasan las variables al jsp
	        request.setAttribute("palabra", palabra);       
	        request.setAttribute("fallosDisponibles", fallosDisponibles);         
	        request.setAttribute("letrasConGuiones", letrasConGuiones); 
	        
	        //meto en la sesion: palabra, fallos dispopnibles y array de letras de la palabra
	        laSesion.setAttribute("palabra", palabra);
	        laSesion.setAttribute("fallosDisponibles", fallosDisponibles);
	        laSesion.setAttribute("letrasConGuiones", letrasConGuiones);
	        
	        /* Las 3 siguientes lineas delegan al JSP pintar el formulario (segun el doGet)*/
			String vista = "/juego.jsp";
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
	        dispatcher.forward(request, response); 
	        
        } catch (Exception e) {
        	e.getMessage();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Lo primero es recuperar la sesion 
			HttpSession laSesion = request.getSession(true);
			//Se recuperan las variables guardadas en la sesion
			String palabra = (String) laSesion.getAttribute("palabra");
			int intentos = (int) laSesion.getAttribute("intentos");
			String[] letras = (String[]) laSesion.getAttribute("letras");
			
			//recupero letra introducida en el "formulario"
			String letraIntroducida = request.getParameter("letraIntroducida");
			
			
			
			
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
