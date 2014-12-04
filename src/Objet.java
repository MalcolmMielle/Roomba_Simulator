// Cr�� par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe definissant tout objet dans la piece.
 * Un objet peut etre nettoyable, et peut etre en collision avec une autre entite.
 * @author Mathieu Nassar et Malcolm Mielle
 */

public class Objet implements Cloneable{
	// Attributs
	//private boolean nettoyable;
	protected Forme forme;
	
	// Constructeurs
	Objet() {
		
	}
	
	/** Construit un Objet a partir de la forme f
	 * @param f
	 */
	Objet(Forme f) {
		forme=f;
	}
	
	// Methodes
	public Forme getForme() {
		return forme;
	}
	
	/**
	 * Methode permettant de savoir si un objet est nettoyable. Retourne faux pour un objet quelconque.
	 * @return false
	 */
	public boolean isCleanable() {
		return false;
	}
	
	/** retourne vrai si l'objet est en collision avec le Roomba defini par ro et dans la posture post.
	 * @param ro  Roomba en question, 
	 * @param post posture a tester.
	 * @return true si le roomba dans cette posture est en collision avec l'objet.
	 */
	public boolean isColliding(Roomba ro, Posture post) {
		return forme.isColliding(ro, post);
	}
	
	public String toString() {
		return("Objet non nettoyable.\n" +forme.toString());
	}
	
	public void paint(MonGraphics g) {
		g.draw(this);
	}
	
	public Object clone() {
		Object o = null;
		try {
			o=(Objet) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
}
