import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class RoombaTest {

	@Test
	public void testdeplace(){
		ArrayList<Capteur> m =new ArrayList<Capteur>();
		Capteur_sal cap = new Capteur_sal(new Posture(1,1,1));
		m.add(cap);
		Roue r1, r2;
		r1= new Roue(); r1.setDistance(10);
		r2= new Roue(); r2.setDistance(20);
		Posture post = new Posture(1,1,0);
		Roomba rob= new Roomba(r1, r2, m, post, new Nettoie(), 5);

		Posture newpos = new Posture(2,1,1);
		rob.deplace(newpos);
		assertTrue(rob.getPosture().getX()==2);
		assertTrue(rob.getPosture().getY()==1);
		assertTrue(rob.getPosture().getTheta()==1);
		
		assertTrue(cap.getPos().getX()==2);
		assertTrue(cap.getPos().getY()==1);
		assertTrue(cap.getPos().getTheta()==2);
		
		assertTrue(rob.roue.get(0).getDistance() == 0);
		assertTrue(rob.roue.get(1).getDistance() == 0);
	}
	
	@Test
	public void testCalculerPosition() {
		Objet obj = new Objet(new Cercle(new Point(100,100), 50));
		Tache obj2 = new Tache(new Cercle(new Point(150,200), 100));
		ArrayList<Objet> liste = new ArrayList<Objet>();
		liste.add(obj);
		liste.add(obj2);
		
		ArrayList<Capteur> mc =new ArrayList<Capteur>();
	    Capteur_prox prox1 = new Capteur_prox(new Posture(45,100,-1*Math.PI/3),18,Math.PI/3);
	    Capteur_prox prox2 = new Capteur_prox(new Posture(45,100,0),18,Math.PI/3);
	    Capteur_prox prox3 = new Capteur_prox(new Posture(45,100,Math.PI/3),18,Math.PI/3);
		Capteur_sal cap = new Capteur_sal(new Posture(45,100,0));
		mc.add(cap);
		mc.add(prox1);
		mc.add(prox2);
		mc.add(prox3);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture p = new Posture(45,100,0); // robot en collision avec obj -> posture differente
		Roomba rob= new Roomba(r1, r2, mc, p, new Nettoie(), 10);
		Posture post = rob.calculerPosition(liste);
		assertFalse(p.getX() == post.getX() && p.getY() == post.getY() && p.getTheta() == post.getTheta());
		
		ArrayList<Capteur> mcb =new ArrayList<Capteur>();
	    Capteur_prox prox1b = new Capteur_prox(new Posture(100,200,-1*Math.PI/3),18,Math.PI/3);
	    Capteur_prox prox2b = new Capteur_prox(new Posture(100,200,0),18,Math.PI/3);
	    Capteur_prox prox3b = new Capteur_prox(new Posture(100,200,Math.PI/3),18,Math.PI/3);
		Capteur_sal capb = new Capteur_sal(new Posture(100,200,0));
		mcb.add(capb);
		mcb.add(prox1b);
		mcb.add(prox2b);
		mcb.add(prox3b);
		Roue r1b, r2b;
		r1b= new Roue();
		r2b= new Roue();
		Posture pb = new Posture(100,200,0); // robot en collision avec tache -> posture egale
		Roomba robb= new Roomba(r1b, r2b, mcb, pb, new Nettoie(), 10);

		Posture post2 = robb.calculerPosition(liste);
		assertTrue(pb.getX() == post2.getX() && pb.getY() == post2.getY() && pb.getTheta() == post2.getTheta());
		
	}
	
	@Test
	public void testNettoyerTache() {
		Tache obj = new Tache(new Cercle(new Point(100,100), 50));
		Tache obj2 = new Tache(new Cercle(new Point(200,200), 29));
		ArrayList<Objet> liste = new ArrayList<Objet>();
		liste.add(obj);
		liste.add(obj2);
		assertTrue(liste.size() == 2);
		
		Roomba rob = new Roomba();
		rob.nettoyerTache(liste, 0); // la tache doit rester
		assertTrue(liste.size() == 2);
		
		rob.nettoyerTache(liste, 1); // la tache doit rester et avoir un rayon de 24
		assertTrue(liste.size() == 2);
		
		rob.nettoyerTache(liste, 1); // la tache doit disparaitre
		assertTrue(liste.size() == 1);
		
	}
	
	@Test
	public void testclone(){
		ArrayList<Capteur> m =new ArrayList<Capteur>();
		Capteur_sal cap = new Capteur_sal(new Posture(1,1,0));
		m.add(cap);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(1,1,0);
		Roomba rob= new Roomba(r1, r2, m, post, new Nettoie(), 5);
		Roomba tob=(Roomba)rob.clone();
				
				
		assertFalse(rob == tob);
		assertTrue(rob.getDiametre() == tob.getDiametre());
		assertTrue(rob.getPosture().getX() == tob.getPosture().getX() );
		assertTrue(rob.getPosture().getY() == tob.getPosture().getY() );
		assertTrue(rob.getPosture().getTheta() == tob.getPosture().getTheta() );
		
		for (int i=0;i<rob.capteur.size();i++)
			assertTrue(rob.capteur.get(i).getPos() == tob.capteur.get(i).getPos());
		
		//Test du deplacement.
		rob.setPosture(new Posture(0,0,0));
		rob.roue.get(0).setDistance(10);
		rob.roue.get(1).setDistance(10);
		assertFalse(tob.getPosture() == rob.getPosture());
		assertFalse(tob.getPosture().getX()==rob.getPosture().getX());
	}
	
	@Test
	public void TestSet(){
		ArrayList<Capteur> m =new ArrayList<Capteur>();
		Capteur_sal cap = new Capteur_sal(new Posture(1,1,0));
		m.add(cap);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(1,1,0);
		Roomba rob= new Roomba(r1, r2, m, post, new Nettoie(), 5);
		
		Roomba rob2=(Roomba) rob.clone();
		
		rob2.setDiametre(52);
		rob.setPostureA(new Posture(10,10,5));
		assertFalse(rob.getPosture().getX()==10);
		assertTrue(rob.clone()!=rob);
		assertTrue(rob.clone().getClass() == rob.getClass());
		assertTrue(rob.getPostureA().getX()==10);
		assertTrue(rob2.getDiametre()==52);

	}

}
