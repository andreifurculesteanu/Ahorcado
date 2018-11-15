package paqueteUtilidades;

public class Utilidades {
	
	public static String generaPalabra() {
		String[] arrayPalabras = {"andrei", "david"};
	/*	String[] arrayPalabras = {"tirana", "berlín", "viena", "bruselas", "minsk", "sarajevo", "sofía", "nicosia"
				, " zagreb", "copenhague", "bratislava", "liubliana", "madrid", "tallin", "helsinki", "parís", "atenas"
				, "budapest", "dublín", "reikiavik", "roma", "pristina", "riga", "vaduz", "vilna", "luxemburgo", "skopie", 
				"chisinau", "monaco", "podgorica", "oslo", "ámsterdam", "varsovia", "lisboa", "londres", "praga", "bucarest", "moscú"
				, "belgrado", "estocolmo", "berna", "kiev", "vaticano"}; */
		int aleatorio = (int) (Math.random()*arrayPalabras.length);
		String palabra = arrayPalabras[aleatorio];
		
		return palabra;
	}
	
	//metodo para cambiar tildes y ñ
	public static String limpiar(String palabra) {
	    String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÒÓÔÕÖØÙÚÛÜİßàáâãäåæçèéêëìíîïğòóôõöøùúûüıÿ";
	    //Cadena de caracteres ASCII que reemplazarán los originales.
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
	
	
	//vuelvo a concatenar las letras de los guiones para ver si es igual que la palabra original
	public static boolean coincidencia(String[] miArray, String pal) {
		String palabraTemporal = "";
		boolean resultado = false;
		for (int i = 0; i < miArray.length; i++) {
			if(palabraTemporal.length()==0) {
				palabraTemporal = miArray[i];
			}else {
				palabraTemporal = palabraTemporal + miArray[i];
			}
		}
		if (palabraTemporal.equals(pal)) {
			resultado = true;
		}
		return resultado;
	}
	
}
