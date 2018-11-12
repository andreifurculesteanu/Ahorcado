

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// se crea una sesión asociada a la petición
        HttpSession laSesion = request.getSession(true);
        
        //genero la palabra
        String palabra = Utilidades.generaPalabra();
        int intentos = 6;
        String letras[] = palabra.split("");
        //se pasan las variables al jsp
        request.setAttribute("palabra", palabra);       
        request.setAttribute("intentos", intentos);       
        request.setAttribute("letras", letras);       

        
        /* Las 3 siguientes lineas delegan al JSP pintar el formulario (segun el doGet)*/
		String vista = "/juego.jsp";
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
