// Cree par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe definissant une tache. Cette classe herite de Objet
 * Une tache est consideree dans le cadre de ce projet comme toujours nettoyable.
 * @author Mathieu Nassar et Malcolm Mielle
 * @see Objet
 */

public class Tache extends Objet {
	// Attributs
	
	// Constructeurs
	Tache() {
		
	}
	
	/** Construit une Tache a partir de la forme f
	 * @param forme
	 */
	Tache(Forme forme) {
		super(forme);
	}
	
	// Methodes
	/**
	 * Methode permettant de savoir si un objet est nettoyable. Retourne true pour une tache.
	 * @return true
	 */
	public boolean isCleanable() {
		return true;
	}
	
	/**
	 * Retourne une taille reduite a un entier dans le but d'etre comparee a d'autres formes.
	 * On peut ainsi caracteriser le niveau de nettoyage d'une forme pour la nettoyer.
	 * @return netLevel int
	 */
	public int getNetLevel() {
		return forme.getHomogeneousSize();
	}
	/**
	 * Fonction de nettoyage, diminue le diametre des taches.
	 */
	public void nettoyer() {
		forme.reduire();
	}
	
	
	public String toString() {
		return("Objet nettoyable.\n" +forme.toString());
	}
	
	public void paint(MonGraphics g) {
		g.draw(this);
	}
	
	public Object clone() {
		Object o=null;
		o=(Tache) super.clone();
		return o;
	}
}
