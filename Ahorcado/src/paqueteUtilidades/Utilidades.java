package paqueteUtilidades;

public class Utilidades {
	
	public static String generaPalabra() {
		String[] arrayPalabras = {"andrei", "david"};
	/*	String[] arrayPalabras = {"tirana", "berl�n", "viena", "bruselas", "minsk", "sarajevo", "sof�a", "nicosia"
				, " zagreb", "copenhague", "bratislava", "liubliana", "madrid", "tallin", "helsinki", "par�s", "atenas"
				, "budapest", "dubl�n", "reikiavik", "roma", "pristina", "riga", "vaduz", "vilna", "luxemburgo", "skopie", 
				"chisinau", "monaco", "podgorica", "oslo", "�msterdam", "varsovia", "lisboa", "londres", "praga", "bucarest", "mosc�"
				, "belgrado", "estocolmo", "berna", "kiev", "vaticano"}; */
		int aleatorio = (int) (Math.random()*arrayPalabras.length);
		String palabra = arrayPalabras[aleatorio];
		
		return palabra;
	}
	
	//metodo para cambiar tildes y �
	public static String limpiar(String palabra) {
	    String original = "����������������������������������������������������������";
	    //Cadena de caracteres ASCII que reemplazar�n los originales.
	    String ascii = "AAAAAAACEEEEIIIIDOOOOOOUUUUYBaaaaaaaceeeeiiiiooooooouuuuyy";
	    for (int i=0; i<original.length(); i++) {
	    //Reemplazamos los caracteres especiales.
	        palabra = palabra.replace(original.charAt(i), ascii.charAt(i));
	    }
		return palabra;
	}
	
	
	//metodo para ver que es una sola letra
	public static boolean letraOK  (String letra) {
		boolean correcto = false;
		if (letra.length() == 1) {
			correcto = true;
		}
		return correcto;
	}
	
	
	//metodo para ver si la letra esta en la palabra
	public static boolean contieneLetra (String letra, String word) {
		boolean correcto = false;
		if(word.contains(letra)) {
			correcto = true;
		}
		return correcto;
	}
	
}
