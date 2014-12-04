import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class PieceTest {

	@Test
	public void testAddObjet() {
		Objet cercle = new Objet(new Cercle(new Point(50,50),50));
		Objet cercle2 = new Objet(new Cercle(new Point(270,270),100));
		Tache tache = new Tache(new Cercle(new Point(150,150),40));

		Piece pi = new Piece(350,350); // Construit 4 murs
		pi.addObjet(cercle);
		pi.addObjet(cercle2);
		pi.addObjet(tache);
		
		assertTrue(pi.getObjet(5).getForme().getCentre().getX() == 270);
		assertTrue(pi.getObjet(4).getForme().getCentre().getX() == 50);
		assertTrue(pi.getObjet(6).getForme().getCentre().getX() == 150);
		assertTrue(pi.getObjet(6).isCleanable());
		assertFalse(pi.getObjet(2).isCleanable());
	}

	@Test
	public void testAddRobot() {
		ArrayList<Capteur> mc =new ArrayList<Capteur>();
	    Capteur_prox prox1 = new Capteur_prox(new Posture(30,300,-1*Math.PI/3),18,Math.PI/3);
	    Capteur_prox prox2 = new Capteur_prox(new Posture(30,300,0),18,Math.PI/3);
	    Capteur_prox prox3 = new Capteur_prox(new Posture(30,300,Math.PI/3),18,Math.PI/3);
		Capteur_sal cap = new Capteur_sal(new Posture(30,300,0));
		mc.add(cap);
		mc.add(prox1);
		mc.add(prox2);
		mc.add(prox3);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(30,150,0);
		Roomba rob= new Roomba(r1, r2, mc, post, new Nettoie(), 34);
		
		Piece pi = new Piece(350,350);
		pi.addRobot(rob);
		
		assertTrue(pi.getRobot().getPosture().getX() == 30);
	}

	@Test
	public void testIsReachable() {
		Objet cercle = new Objet(new Cercle(new Point(50,50),50));
		Objet cercle2 = new Objet(new Cercle(new Point(270,270),100));
		Tache tache = new Tache(new Cercle(new Point(150,150),40));

		Piece pi = new Piece(350,350); // Construit 4 murs
		pi.addObjet(cercle);
		pi.addObjet(cercle2);
		pi.addObjet(tache);
		
		Posture post = new Posture(150,150,0);
		assertTrue(pi.isReachable(post));
		pi.getRobot().setPosture(new Posture(270,270,0));
		assertFalse(pi.isReachable(pi.getRobot().getPosture()));
		
		
	}

}
