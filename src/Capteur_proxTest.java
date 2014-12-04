import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Capteur_proxTest {

	@Test
	public void testType(){
		Capteur_prox cap = new Capteur_prox();
		assertEquals("Proximite", cap.type());
	}

	/**
	 * On test la bonne detection du capteur de proximite.
	 * Il ne detecte pas les taches mais bien l'objet devant lui.
	 */	
	@Test
	public void testDetecte(){
		Capteur_prox cap = new Capteur_prox(new Posture(0,0,0),2,Math.PI);
		Objet cercle3 = new Objet(new Cercle(new Point(0,-4),4));
		Objet cercle4 = new Objet(new Cercle(new Point(-4,0),2));
		Tache tache = new Tache(new Cercle(new Point(0,0),40));
		Tache tache2 = new Tache(new Cercle(new Point(0,0),50));
		Tache tache3 = new Tache(new Cercle(new Point(0,0),30));
		ArrayList<Objet> obj=new ArrayList<Objet>();
		obj.add(cercle3);
		obj.add(cercle4);
		obj.add(tache);
		obj.add(tache2);
		obj.add(tache3);
		int i=cap.detecter(obj);
		assertEquals(i,0);
	}
	
	@Test
	public void testclone(){
		Posture p= new Posture(1,3,0);
		Capteur_prox cap= new Capteur_prox(p, 10, Math.PI);
		assertTrue(cap.clone()!=cap);
		assertTrue(cap.clone().getClass() == cap.getClass());
	}
}

