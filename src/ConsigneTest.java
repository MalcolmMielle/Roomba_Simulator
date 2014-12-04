import static org.junit.Assert.*;

import org.junit.Test;


public class ConsigneTest {

	@Test
	public void testSize() {
		Consigne cons = new Consigne();
		assertTrue(cons.size() == 0);
		cons.addConsigne(3);
		cons.addConsigne(54);
		assertTrue(cons.size() == 2);
	}

	@Test
	public void testGetConsigne() {
		Consigne cons = new Consigne();
		cons.addConsigne(3);
		cons.addConsigne(54);
		assertTrue(cons.getConsigne(1) == 54);
	}
	
	@Test
	public void testClone() {
		Consigne cons = new Consigne();
		cons.addConsigne(3);
		cons.addConsigne(54);
		Consigne cons2 = (Consigne) cons.clone();
		assertTrue(cons2.getConsigne(1) == 54);
	}
}
