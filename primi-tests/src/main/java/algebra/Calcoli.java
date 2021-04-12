package algebra;

public class Calcoli {

	public static void main(String[] args) {
		// calcolare l'area di un trapezio con base magg 5  altezza 8
		// e base minore 3
		
		// chiamata di metodo
		System.out.print("L'area del trapezio è: ");

		System.out.println(quoziente(prodotto(somma(5, 3), 8), 2));
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
