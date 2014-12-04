import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class CercleTest {

	@Test
	public void testIsColliding() {
		ArrayList<Capteur> m =new ArrayList<Capteur>();
		Capteur_sal cap = new Capteur_sal(new Posture(1,2,0));
		m.add(cap);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(1,1,0);
		Roomba rob= new Roomba(r1, r2, m, post,new Nettoie(), 6);

		Cercle cercle = new Cercle(new Point(1,1),10); // le robot penetre dans le cercle -> true
		Cercle cercle2 = new Cercle(new Point(10,10),1); // le cercle n'est pas en collision -> false
		Cercle cercle3 = new Cercle(new Point(5,1), 2); // le cercle est en contact ponctuel -> true
		
		assertTrue(cercle.isColliding(rob, post));
		assertFalse(cercle2.isColliding(rob, post));
		assertTrue(cercle3.isColliding(rob, post));
	}
	
	/**
	 * On test la collision avc un capteur de proximite.
	 * On verifie notement les valeurs extremales.
	 */	
	@Test
	public void testCollide() {
		Posture po = new Posture (1, 0, 0);
		Cercle c = new Cercle(new Point(0,0), 10);
		Capteur_prox Cap= new Capteur_prox(po,5,Math.PI);
		assertFalse(c.collide(Cap));
		Posture po1 = new Posture (0.1, 0, 0);
		Cap.setPos(po1);
		assertFalse(c.collide(Cap));
		po1 = new Posture (0, 0, 0);
		Cap.setPos(po1);
		Cercle c1 = new Cercle(new Point(7,0),10);
		assertTrue(c1.collide(Cap));
		c1 = new Cercle(new Point(0,7),10);
		assertTrue(c1.collide(Cap));
		
	}
	/**
	 * On réduit une cercle et on verifie bien la reduction maximum a 1.
	 */
	@Test
	public void testReduire() {
		Cercle r = new Cercle(new Point(4,0), 60);
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		r.reduire();
		assertTrue(r.getHomogeneousSize() == 1);
	}
	
	@Test
	public void testClone() {
		Cercle cercle = new Cercle(new Point(1,1),10);
		Cercle c2 = (Cercle) cercle.clone();
		assertFalse(c2==cercle);
		assertTrue(c2.getClass() == cercle.getClass());
		assertTrue(c2.getDiametre()==10);
		assertTrue(c2.getCentre().getX()==1);
	}

}
