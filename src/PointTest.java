import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {

	@Test
	public void testGetX() {
		Point p2 =new Point(2, 3);
		assertEquals (p2.getX(), 2, 0.00001);	
	}

	@Test
	public void testGetY() {
		Point p2 =new Point(2, 3);
		assertEquals(p2.getY(),3, 0.00001);
	}
	
	/**
	 * Test de la projection d'un point sur un segment.
	 * On verifie lorsque le point projete est :
	 * a l'exterieur,
	 * a l'interieur,
	 * sur un  bord.
	 */	
	@Test
	public void testProjectionColliding() {
		Point A = new Point(10,10);
		Point B = new Point(15,10);
		Point C = new Point(5,5); // projection sur AB exterieure -> False
		Point D = new Point(12,5); // projection sur AB interieure -> True
		Point E = new Point(20,5); // projection sur AB exterieure -> False
		Point F = new Point(10,5); // projection sur AB peripherique -> True
		Point G = new Point(10,10); // projection sur AB = A -> True
		
		assertFalse(C.projectionColliding(A, B));
		assertTrue(D.projectionColliding(A, B));
		assertFalse(E.projectionColliding(A, B));
		assertTrue(F.projectionColliding(A, B));
		assertTrue(G.projectionColliding(A, B));
	}

	@Test
	public void testclone(){
		Point cap= new Point(1,3);
		assertTrue(cap.clone()!=cap);
		assertTrue(cap.clone().getClass() == cap.getClass());
		assertTrue(cap.clone().getClass() == cap.getClass());
	}
}
