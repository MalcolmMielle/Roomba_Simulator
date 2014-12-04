import static org.junit.Assert.*;

import org.junit.Test;


public class TacheTest {

	@Test
	public void testIsCleanable() {
		Tache t = new Tache(new Cercle(new Point(1,1),10));
		assertTrue(t.isCleanable());
	}
	
	@Test
	public void testNettoyage() {
		Tache t = new Tache(new Cercle(new Point(1,1),100));
		assertTrue(t.getNetLevel() == 100);
		t.nettoyer();
		assertTrue(t.getNetLevel() == 90);
		
		Tache t2 = new Tache(new Cercle(new Point(1,1),20));
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		t2.nettoyer();
		assertTrue(t2.getNetLevel() == 1);
	}

	@Test
	public void testClone() {
		Tache t = new Tache(new Cercle(new Point(1,1),10));
		Tache t2 = (Tache) t.clone();
		assertTrue(t2.getForme().getCentre().getX()==1);
		assertTrue(t2.getForme().getCentre().getY()==1);
	}

}
