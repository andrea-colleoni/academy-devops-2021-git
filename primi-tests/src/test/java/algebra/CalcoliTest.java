package algebra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcoliTest {

	@Test
	public void testSomma() {
		int a = 10;
		int b = 15;
		int valoreAtteso = 24;
		int valoreCalcolato = Calcoli.somma(a, b);
		assertEquals(valoreAtteso, valoreCalcolato);
	}

	@Test
	public void testProdotto() {
		int a = 4;
		int b = 8;
		int valoreAtteso = 32;
		int valoreCalcolato = Calcoli.prodotto(a, b);
		assertEquals(valoreAtteso, valoreCalcolato);
	}

	@Test
	public void testQuoziente() {
		int a = 30;
		int b = 6;
		int valoreAtteso = 5;
		int valoreCalcolato = Calcoli.quoziente(a, b);
		assertEquals(valoreAtteso, valoreCalcolato);
	}

}
