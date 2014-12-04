import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.util.Scanner; 
public class Environnement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Bienvenue.");
		boolean g=false;
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println("Choix");
		System.out.println("1. Mode texte seul");
		System.out.println("2. Mode texte graphique.");
		int ent = saisieUtilisateur.nextInt();
		switch (ent) {
			case 1: g=false; break;
			default: g=true; break;
		}
		
		Environnement en = new Environnement();
		
		System.out.println("Choix piece");
		System.out.println("1. piece predefinie");
		System.out.println("2. piece aleatoire");
		ent = saisieUtilisateur.nextInt();
		Piece pi = null;
		switch (ent) {
			case 1: pi=en.initPiece(); break;
			case 2: pi=en.initPieceAlea(); break;
			default: pi=en.initPiece(); break; 
		}
		
		System.out.println("Initialisation de la piece.");
		System.out.println(pi);

		MonGraphics2D m = null;
		
		if (g) {
			m = new MonGraphics2D();
			JFrame ma_fenetre = new JFrame("Simulateur de Roomba");
		    m.setPreferredSize(new Dimension(400, 400));
		    ma_fenetre.setContentPane(m);
		    ma_fenetre.pack();
		    ma_fenetre.setVisible(true);
		    m.setPiece(pi);
		} 
		
	    while (true)
	    {
	    	pi.animate();
	    	System.out.println(pi.getRobot());
			// attend 0.01 sec
			try  { Thread.sleep(10); }
			catch (Exception e) {}
			if (g)
				m.repaint(); // redessine (appelle entre autres paint())
	    }
	}
	
	public Piece initPiece() {
		ArrayList<Capteur> mc =new ArrayList<Capteur>();
	    Capteur_prox prox1 = new Capteur_prox(new Posture(30,150,-1*Math.PI/3),18,Math.PI/3);
	    Capteur_prox prox2 = new Capteur_prox(new Posture(30,150,0),18,Math.PI/3);
	    Capteur_prox prox3 = new Capteur_prox(new Posture(30,150,Math.PI/3),18,Math.PI/3);
		Capteur_sal cap = new Capteur_sal(new Posture(30,150,0));
		mc.add(cap);
		mc.add(prox1);
		mc.add(prox2);
		mc.add(prox3);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(30,150,0);
		Roomba rob= new Roomba(r1, r2, mc, post, new Nettoie(), 34);
		
		Objet cercle = new Objet(new Cercle(new Point(50,50),50));
		Objet cercle2 = new Objet(new Cercle(new Point(270,270),100));
		Objet cercle3 = new Objet(new Cercle(new Point(300,20),35));
		Objet cercle4 = new Objet(new Cercle(new Point(70,250),50));
		Tache tache = new Tache(new Cercle(new Point(150,150),40));
		Tache tache2 = new Tache(new Cercle(new Point(234,0),50));
		Tache tache3 = new Tache(new Cercle(new Point(100,100),30));
		Tache tache4 = new Tache(new Cercle(new Point(45,260),20));

		Piece pi = new Piece(400,400);
		pi.addRobot(rob);
		pi.addObjet(cercle);
		pi.addObjet(cercle2);
		pi.addObjet(cercle3);
		pi.addObjet(cercle4);
		pi.addObjet(tache);
		pi.addObjet(tache2);
		pi.addObjet(tache3);
		pi.addObjet(tache4);
		
		return pi;
	}
	
	public Piece initPieceAlea() {
		Piece pi = new Piece(400,400);
		
		ArrayList<Capteur> mc =new ArrayList<Capteur>();
	    Capteur_prox prox1 = new Capteur_prox(new Posture(30,150,-1*Math.PI/3),18,Math.PI/3);
	    Capteur_prox prox2 = new Capteur_prox(new Posture(30,150,0),18,Math.PI/3);
	    Capteur_prox prox3 = new Capteur_prox(new Posture(30,150,Math.PI/3),18,Math.PI/3);
		Capteur_sal cap = new Capteur_sal(new Posture(30,150,0));
		mc.add(cap);
		mc.add(prox1);
		mc.add(prox2);
		mc.add(prox3);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(30,150,0);
		Roomba rob= new Roomba(r1, r2, mc, post, new Nettoie(), 34);
		pi.addRobot(rob);
		
		pi.fillAlea();
		return pi;
	}

}
