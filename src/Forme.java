// Cree par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe abstraite representant la forme des objets.
 * @author Mathieu Nassar, Malcolm Mielle
 * @see Objet, Cercle, Rectangle
 */

public abstract class Forme implements Cloneable {
	// Attributs
	protected Point centre;
	
	// Constructeurs
	/**
	 * Constructeur vide
	 */
	Forme() {
		centre = new Point(0,0);
	}
	
	/**
	 * Constructeur de Forme. Initialise le centre de la forme � cen.
	 * @param cen
	 */
	Forme(Point cen) {
		centre = cen;
	}
	
	// M�thodes
	/**
	 * Retourne le centre de la Forme, sous la forme d'un objet de type Point
	 * @return Point
	 */
	public Point getCentre() {
		return centre;
	}
	
	public void setCentre(Point p) {
		centre = p;
	}
	
	/** isColliding retourne vrai si roomba designe par ro et dans la posture post est en collision
	 * avec la forme.
	 * @param ro Roomba en question, post posture a tester.
	 */
	public abstract boolean isColliding(Roomba ro, Posture post);
	
	/**
	 * Collide retourne la collision entre la forme et un capteur de proximite passe en parametre.
	 * @param cpt
	 * @return boolean
	 */
	public abstract boolean collide(Capteur_prox cpt);
	
	/**
	 * Retourne une taille r�duite a un entier dans le but d'etre comparee a d'autres formes.
	 */
	public abstract int getHomogeneousSize();
	
	/**
	 * r�duit la taille de la forme de 10%;
	 */
	public abstract void reduire();
	
	public abstract String toString();
	
	public abstract void paint(MonGraphics g);
	
	public Object clone() {
		Object o = null;
		try {
			o=(Forme) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
}