// Cree par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe representant la piece a nettoyer.
 * La piece contient les objet incluant les taches, et un robot.
 * Elle est egalement definie par sa largeur et sa longueur.
 * La piece se trouve dans une scene.
 * @author Mathieu Nassar et Malcolm Mielle
 * @See Objet, Robot, Roomba, Scene
 */

import java.util.ArrayList;

public class Piece implements Cloneable{
	// attributs
	private int largeur;
	private int longueur;
	private ArrayList<Objet> objets;
	private Robot rob;
	
	// Constructeurs
	
	/**
	 * Constructeur par defaut de la Piece.
	 * Ajoute un robot de base avec aucun capteur et des dimensions predefinies.
	 */
	Piece() {
		objets = new ArrayList<Objet>();
		largeur = 100;
		longueur = 100;
		rob = new Roomba();
		
		Objet mur1 = new Objet(new Rectangle(new Point(2,(int)longueur/2),longueur,4));
		Objet mur2 = new Objet(new Rectangle(new Point((int)largeur/2,2),4,largeur));
		Objet mur3 = new Objet(new Rectangle(new Point(largeur,(int)longueur/2),longueur,4));
		Objet mur4 = new Objet(new Rectangle(new Point((int) largeur/2,longueur),4,largeur));
		addObjet(mur1);
		addObjet(mur2);
		addObjet(mur3);
		addObjet(mur4);
	}
	
	/**
	 * Constructeur de Piece.
	 * Prend en arguments la largeur et la longueur de la piece.
	 * @param lar
	 * @param lon
	 */
	Piece(int lar, int lon) {
		largeur = lar;
		longueur = lon;
		objets = new ArrayList<Objet>();
		rob = new Roomba();
		
		Objet mur1 = new Objet(new Rectangle(new Point(2,(int)longueur/2),longueur,4));
		Objet mur2 = new Objet(new Rectangle(new Point((int)largeur/2,2),4,largeur));
		Objet mur3 = new Objet(new Rectangle(new Point(largeur,(int)longueur/2),longueur,4));
		Objet mur4 = new Objet(new Rectangle(new Point((int) largeur/2,longueur),4,largeur));
		addObjet(mur1);
		addObjet(mur2);
		addObjet(mur3);
		addObjet(mur4);
	}
	
	/**
	 * Constructeur de Piece.
	 * Prend en arguments la largeur et la longueur de la piece, ainsi qu'une liste d'objets.
	 * @param lar
	 * @param lon
	 * @param obj la Liste des objets
	 * @param robot le Robot dans la piece
	 */
	Piece(int lar, int lon, ArrayList<Objet> obj, Robot robot) {
		largeur = lar;
		longueur = lon;
		objets = obj;
		rob = robot;
		
		Objet mur1 = new Objet(new Rectangle(new Point(2,(int)longueur/2),longueur,4));
		Objet mur2 = new Objet(new Rectangle(new Point((int)largeur/2,2),4,largeur));
		Objet mur3 = new Objet(new Rectangle(new Point(largeur,(int)longueur/2),longueur,4));
		Objet mur4 = new Objet(new Rectangle(new Point((int) largeur/2,longueur),4,largeur));
		addObjet(mur1);
		addObjet(mur2);
		addObjet(mur3);
		addObjet(mur4);
	}
	
	// Methodes
	
	/**
	 * Remplit une piece d'objets aleatoires.
	 */
	public void fillAlea() {
		// remplissage d'obstacles
		int obstacles_min=1, obstacles_max=13;
		int nb = (int) (Math.random() * (obstacles_max-obstacles_min) + obstacles_min);
		for (int i=0;i<nb/2;i++) {
			int x = (int) (Math.random() * (largeur-40-40)+40);
			int y = (int) (Math.random() * (longueur-40-40)+40);
			int d = (int) (Math.random() *(100-20)+20);
			Objet obj = new Objet(new Cercle(new Point(x,y),d));
			if (!obj.isColliding((Roomba)rob, rob.getPosture()))
			addObjet(obj);
		}
		for (int i=0;i<nb/2;i++) {
			int x = (int) (Math.random() * (largeur-40-40)+40);
			int y = (int) (Math.random() * (longueur-40-40)+40);
			int longueur = (int) (Math.random() *(100-20)+20);
			int largeur = (int) (Math.random() *(100-20)+20);
			Objet obj = new Objet(new Rectangle(new Point(x,y),longueur, largeur));
			if (!obj.isColliding((Roomba)rob, rob.getPosture()))
			addObjet(obj);
		}
		
		// remplissage de taches (berk)
		int taches_min=5, taches_max=300;
		nb = (int)( Math.random() * (taches_max-taches_min) + taches_min);
		for (int i=0;i<nb;i++) {
			int x = (int) (Math.random() * (largeur-40-40)+40);
			int y = (int) (Math.random() * (longueur-40-40)+40);
			int d = (int) (Math.random() *(100-20)+20);
			Tache obj = new Tache(new Cercle(new Point(x,y),d));
			addObjet(obj);
		}
	}
	
	/**
	 * Ajoute un Objet dans la liste des objets presents dans la piece.
	 * @param obj
	 */
	public void addObjet(Objet obj) {
		objets.add(obj);
	}
	
	/**
	 * Ajoute un robot dans la piece.
	 * @param robot : le robot a ajouter dans la piece.
	 */
	public void addRobot(Robot robot) {
		rob = robot;
	}
	
	public Robot getRobot() {
		return rob;
	}
	
	/**
	 * Supprime l'objet d'indice ind de la liste des objets.
	 * Permet notamment la suppression des taches une fois nettoyees.
	 * @param ind
	 */
	public void removeObjet(int ind) {
		objets.remove(ind);
	}
	
	/**
	 * Retourne l'objet designe par l'indice ind.
	 * @param ind
	 * @return Objet
	 */
	public Objet getObjet(int ind) {
		return objets.get(ind);
	}
	
	/**
	 * Anime la scene se deroulant dans la piece.
	 * En particulier, genere une nouvelle position du robot.
	 */
	public void animate() {
		Posture newpost = rob.calculerPosition(objets);
		if (isReachable(newpost))
			rob.deplace(newpost);
	}
	
	/**
	 * Cette fonction a pour but de tester si la position calculee par le comportement du robot
	 * n'entre pas en collision avec un obstacle de la piece (mur, objets).
	 * Si cette nouvelle posture est incorrecte, le robot ne pourra s'y deplacer. Mais tant que ses capteurs
	 * ne le lui indiquent pas, celui-ci ne le saura pas.
	 * @param p Posture a tester
	 * @return true si le robot peut se deplacer a l'endroit desire, false sinon.
	 */
	public boolean isReachable(Posture p) {
		if (p.getX() <= 0 || p.getY() <= 0 || p.getX() > largeur || p.getY() > longueur) {
			System.out.println("Mur.");
			return false;
		}
		for (int i=0;i<objets.size();i++){
			if (!objets.get(i).isCleanable() && objets.get(i).isColliding((Roomba) rob, p))
				return false;
		}
		return true;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getLongueur() {
		return longueur;
	}
	
	public String toString() {
		String res = new String("Piece de longueur " +longueur+ "m et de largeur " +largeur+ "m.\n");
		res = res + "Liste des objets: \n";
		for(int i=0;i<objets.size();i++) {
			res = res + objets.get(i).toString() + "\n";
		}
		res = res + "Robot present :\n";
		res = res + rob.toString();
		return res;
	}
	
	public void paint(MonGraphics g) {
		g.draw(this);
		for (int i=0;i<objets.size();i++)
			objets.get(i).paint(g);
		rob.paint(g);
	}
	
	public Object clone() {
		Object o = null;
		try {
			o=(Piece) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
}
