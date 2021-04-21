package algebra;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Calcoli {
	
	private static Logger log = LogManager.getLogger("algebra");

	public static void main(String[] args) {
		// calcolare l'area di un trapezio con base magg 5  altezza 8
		// e base minore 3
		
		// chiamata di metodo
		log.info("L'area del trapezio è: " + quoziente(prodotto(somma(5, 3), 8), 2));
	}
	
	// un meotodo che fa la somma
	public static int somma(int a, int b) {
		return a + b ;
	}
	
	// un altro metodo
	public static int prodotto(int a, int b) {
		return a * b;
	}
	//...
	public static int quoziente(int a, int b) {
		return a / b;
	}

}
