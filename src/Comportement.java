// Cree par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe definissant les comportements en general.
 * Pour l'utiliser, il faut faire heriter un comportement de cette classe et redefinir la methode generique().
 * Chaque robot possede un comportement qui peut etre change a volonte.
 * @author Mathieu Nassar et Malcolm Mielle
 * @See Robot
 */

import java.util.ArrayList;

public abstract class Comportement implements Cloneable {
	// Attributs
	
	// Constructeurs
	Comportement() {
	}
	
	// Methodes
	/**
	 * methode appelee par le robot.
	 * Les classes derivant de Comportement implementent cette methode pour susciter le deplacement du robot.
	 * @param capteurs la liste des capteur
	 * @param capteursAns la liste des reponses des capteurs.
	 * @return Consigne: consignes des roues donnant lieu au deplacement du robot.
	 */
	public abstract Consigne generique(ArrayList<Capteur> capteurs, int[] capteursAns);
	public abstract String toString();
	
	public Object clone() {
		Object o = null;
		try {
			o=(Comportement) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
}
