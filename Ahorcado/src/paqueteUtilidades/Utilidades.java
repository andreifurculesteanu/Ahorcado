
public class Utilidades {
	
	public static String generaPalabra() {
		String[] arrayPalabras = {"andrei", "daniel", "adrian", "miguel", "maria", "david"};
		int aleatorio = (int) (Math.random()*arrayPalabras.length);
		String palabra = arrayPalabras[aleatorio];
		
		return palabra;
	}
}
