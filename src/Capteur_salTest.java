import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class Capteur_salTest {

	@Test
	public void testConstr() {
		Posture po = new Posture (2, 3,0);
		Capteur_sal Cap= new Capteur_sal(po);
		assertEquals(Cap.getPos(), po);
		assertEquals(Cap.getPos().getX(), po.getX(), 0.00001);
		assertEquals(Cap.getPos().getY(), po.getY(), 0.00001);		
	}
	
	/**
	 * Test de la collision d'un cercle avec un autre.
	 * On traite toutes les valeurs extremales selon les axes.
	 */	
	@Test
	public void testCollideC(){
		Posture po = new Posture (1, 0, 0);
		Cercle c = new Cercle(new Point(0,0), 20);
		Capteur_sal Cap= new Capteur_sal(po);
		assertTrue(Cap.collideC(c));
		Posture po1 = new Posture (12.5, 0, 0);
		Cap.setPos(po1);
		assertTrue(Cap.collideC(c));
		po1 = new Posture (12.6, 0, 0);
		Cap.setPos(po1);
		assertFalse(Cap.collideC(c));
		po1 = new Posture (-12.5, 0, 0);
		Cap.setPos(po1);
		assertTrue(Cap.collideC(c));
		po1 = new Posture (-12.6, 0, 0);
		Cap.setPos(po1);
		assertFalse(Cap.collideC(c));
		po1 = new Posture (0, 12.5, 0);
		Cap.setPos(po1);
		assertTrue(Cap.collideC(c));
		po1 = new Posture (0, 12.6, 0);
		Cap.setPos(po1);
		assertFalse(Cap.collideC(c));
		po1 = new Posture (0, -12.5, 0);
		Cap.setPos(po1);
		assertTrue(Cap.collideC(c));
		po1 = new Posture (0, -12.6, 0);
		Cap.setPos(po1);
		assertFalse(Cap.collideC(c));
		
		po.setX(0);
		po.setY(2);
		po1.setX(0);
		po1.setY(0);
		assertTrue(Cap.collideC(c));
		po1.setY(-5);
		assertTrue(Cap.collideC(c));
	}
	
	/**
	 * On test que le capteur renvoi bien l'indice de la tache et pas d'un objet.
	 */	
	@Test
	public void testdetecter(){
		ArrayList<Objet> List = new ArrayList<Objet>();
		Cercle c1=new Cercle();
		Objet ob=new Objet(c1);
		List.add(ob);
		Cercle c2=new Cercle();
		Objet ob2=new Objet(c2);
		List.add(ob2);
		Cercle c3=new Cercle();
		Objet ob3=new Objet(c3);
		List.add(ob3);
		Cercle c4=new Cercle();
		Objet ob4=new Objet(c4);
		List.add(ob4);
		Cercle c5=new Cercle();
		Objet ob5=new Objet(c5);
		List.add(ob5);
		Cercle c6=new Cercle();
		Objet ob6=new Objet(c6);
		List.add(ob6);
		Cercle c7=new Cercle(new Point(4,10), 2);
		Tache ta=new Tache(c7);
		List.add(ta);
		
		for(int i=0;i<6;i++){
			List.get(i).getForme().getCentre().setX(10*Math.random());
			List.get(i).getForme().getCentre().setY(10*Math.random());
		}
		Posture p= new Posture(10,10,0);
		Capteur_sal Cap= new Capteur_sal(p);
		assertTrue(Cap.detecter(List)==-1);
		Cap.setPos(new Posture(4.5,10,0));
		assertTrue(Cap.detecter(List)==6);
		
	}

	@Test
	public void testclone(){
		Posture p= new Posture(1,3,0);
		Capteur_sal cap= new Capteur_sal(p);
		assertTrue(cap.clone()!=cap);
		assertTrue(cap.clone().getClass() == cap.getClass());
		
	}
}
