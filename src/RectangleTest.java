import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class RectangleTest {

	@Test
	public void testIsCollidingRoomba() {
		ArrayList<Capteur> m = new ArrayList<Capteur>();
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(10,10,0);
		Roomba rob= new Roomba(r1, r2, m, post,new Nettoie(), 4);
		
		Rectangle rectangle = new Rectangle(new Point(7,7),6,6); // le robot p�n�tre dans le rectangle -> true
		Rectangle rectangle1 = new Rectangle(new Point(10,10),10,10); // le robot est dans le rectangle -> true
		Rectangle rectangle2 = new Rectangle(new Point(30,30),4,4); // le cercle n'est pas en collision -> false
		Rectangle rectangle3 = new Rectangle(new Point(10,6), 4,4); // le cercle est en contact ponctuel -> true
		Rectangle rectangle4 = new Rectangle(new Point(6,6),4,4); //  -> false
		Rectangle rectangle5 = new Rectangle(new Point(10,12),4,4); //  -> true
		
		assertTrue(rectangle.isColliding(rob,post));
		assertTrue(rectangle1.isColliding(rob,post));
		assertFalse(rectangle2.isColliding(rob,post));
		assertTrue(rectangle3.isColliding(rob,post));
		assertFalse(rectangle4.isColliding(rob,post));
		assertTrue(rectangle5.isColliding(rob,post));
	}
	
	@Test
	public void testIsCollidingCercle() {
		Cercle c = new Cercle(new Point(10,10),4);

		Rectangle rectangle = new Rectangle(new Point(7,7),6,6); // le robot p�n�tre dans le rectangle -> true
		Rectangle rectangle1 = new Rectangle(new Point(10,10),10,10); // le robot est dans le rectangle -> true
		Rectangle rectangle2 = new Rectangle(new Point(30,30),4,4); // le cercle n'est pas en collision -> false
		Rectangle rectangle3 = new Rectangle(new Point(10,6), 4,4); // le cercle est en contact ponctuel -> true
		Rectangle rectangle4 = new Rectangle(new Point(6,6),4,4); //  -> false
		Rectangle rectangle5 = new Rectangle(new Point(10,12),4,4); //  -> true
		
		assertTrue(rectangle.isColliding(c));
		assertTrue(rectangle1.isColliding(c));
		assertFalse(rectangle2.isColliding(c));
		assertTrue(rectangle3.isColliding(c));
		assertFalse(rectangle4.isColliding(c));
		assertTrue(rectangle5.isColliding(c));
	}

	@Test
	public void testIsCollidingRectangle() {
		Rectangle rect1 = new Rectangle(new Point(5,5),2,2);
		Rectangle rect2 = new Rectangle(new Point(7,7),2,2); // collision par contact
		Rectangle rect3 = new Rectangle(new Point(7,7),3,4); // collision par chevauchement
		Rectangle rect4 = new Rectangle(new Point(7,7),8,8); // collision par englobement
		Rectangle rect5 = new Rectangle(new Point(17,17),2,2); // pas de collision
		
		assertTrue(rect1.isColliding(rect2));
		assertTrue(rect1.isColliding(rect3));
		assertTrue(rect1.isColliding(rect4));
		assertFalse(rect1.isColliding(rect5));
	}
	
	@Test
	public void testIsCollidingPoint() {
		Rectangle rectangle = new Rectangle(new Point(10,10),5,5);
		assertTrue(rectangle.isColliding(new Point(10,10)));
		assertFalse(rectangle.isColliding(new Point(10,20)));
		assertFalse(rectangle.isColliding(new Point(0,0)));
		assertFalse(rectangle.isColliding(new Point(20,10)));
	}

	/**
	 * Test de la collision avec le capteur de proximite.
	 * On test une collision sur tous les cotes du rectangle.
	 * Puis on test la collision avec chacun des coin du rectangle ses coins constituant le 3eme cas traite par la fonction.
	 */
	@Test
	public void testCollideCapProx() {
		Posture po = new Posture (4, 2, Math.PI);
		Rectangle r = new Rectangle(new Point(0,0), 4,4);

		Capteur_prox cap= new Capteur_prox(po,2,Math.PI);
		assertTrue(r.collide(cap));

		Posture po1 = new Posture (4.1, 2, 0);
		cap.setPos(po1);
		assertFalse(r.collide(cap));	

		cap=new Capteur_prox(new Posture(-4,2,0),2,Math.PI);
		assertTrue(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(-4,-2,0),2,Math.PI);
		assertTrue(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(+4,-2,Math.PI/2),2,Math.PI);
		assertTrue(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(+4.1,-2,Math.PI/2),2,Math.PI);
		assertFalse(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(0,-4,-Math.PI),2,Math.PI);
		assertTrue(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(-4,0,0),2,Math.PI);
		assertTrue(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(2.1,0,0),2,Math.PI);
		assertFalse(r.collide(cap));
		
		cap=new Capteur_prox(new Posture(2,0,0),2,Math.PI);
		assertFalse(r.collide(cap));
	}
	
	
	@Test
	/**
	 *  Meme test que pour le cercle.
	 */
	public void testReduire() {
		Rectangle r = new Rectangle(new Point(4,0), 40,60);
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
	public void testClone(){
		Rectangle cercle = new Rectangle(new Point(1,1),10,10);
		Rectangle c2 = (Rectangle) cercle.clone();
		assertFalse(c2==cercle);
		assertTrue(c2.getClass() == cercle.getClass());
		assertTrue(c2.getLargeur()==10);
		assertTrue(c2.getLongueur()==10);
		assertTrue(c2.getCentre().getX()==1);
	}

}
