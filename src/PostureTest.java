import static org.junit.Assert.*;

import org.junit.Test;


public class PostureTest {

	@Test
	public void testGetX() {
		Posture p1= new Posture (0, 1, 3);
		assertEquals(p1.getY(),1,0.0000001);
		assertEquals(p1.getX(),0,0.0000001);
		assertEquals(p1.getTheta(),3,0.0000001);
	}
	
	@Test
	public void testsetX() {
		Posture p1= new Posture (2, 0, 0);
		p1.setX(0);
		p1.setY(1);
		p1.setTheta(3);
		assertEquals(p1.getY(),1,0.0000001);
		assertEquals(p1.getX(),0,0.0000001);
		assertEquals(p1.getTheta(),3,0.0000001);
	}
	
	@Test
	public void testMove(){
		Posture p= new Posture(0,0,0);
		p.move(10, 10, 2);
		assertTrue(p.getX()==10);
		assertTrue(p.getY()==0);
		assertTrue(p.getTheta()==0);

		p.move(10, -10, 2);
		assertTrue(p.getX()==10);
		assertTrue(p.getY()==0);

	}

}
