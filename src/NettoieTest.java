import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class NettoieTest {
	/**
	 * On verifie un comportement de base en donnant certaines valeurs particulieres.
	 */
	@Test

	public void testGenerique() {
		ArrayList<Capteur> mc =new ArrayList<Capteur>();
	    Capteur_prox prox1 = new Capteur_prox(new Posture(30,150,-1*Math.PI/3),30,Math.PI/3);
	    Capteur_prox prox2 = new Capteur_prox(new Posture(30,150,0),30,Math.PI/3);
	    Capteur_prox prox3 = new Capteur_prox(new Posture(30,150,Math.PI/3),30,Math.PI/3);
		Capteur_sal cap = new Capteur_sal(new Posture(30,150,0));
		mc.add(prox1);
		mc.add(prox2);
		mc.add(prox3);
		mc.add(cap);
		
		int[] cptans = {1,1,1,3};
		Nettoie comp = new Nettoie();
		Consigne cons = comp.generique(mc, cptans);
		assertTrue((int)cons.getConsigne(0) == 3);
		
		int[] cptans2 = {1,1,1,-1};
		Consigne cons2 = comp.generique(mc, cptans2);
		assertTrue(cons2.getConsigne(0) == -1*cons2.getConsigne(1));
		
		int[] cptans3 = {-1,-1,1,-1};
		Consigne cons3 = comp.generique(mc, cptans3);
		assertTrue(cons3.getConsigne(0) == -1*cons3.getConsigne(1));
		
		int[] cptans4 = {-1,-1,-1,-1};
		Nettoie comp2 = new Nettoie();
		Consigne cons4 = comp2.generique(mc, cptans4);
		assertTrue(cons4.getConsigne(0) == cons4.getConsigne(1));
	}

}
