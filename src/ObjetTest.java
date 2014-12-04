import static org.junit.Assert.*;

import org.junit.Test;


public class ObjetTest {

	@Test
	public void testIsCleanable() {
		Objet o = new Objet(new Cercle(new Point(1,1),10));
		assertFalse(o.isCleanable());
	}

	@Test
	public void testClone() {
		Objet cap= new Objet();
		assertTrue(cap.clone()!=cap);
		assertTrue(cap.clone().getClass() == cap.getClass());
	}

}
