import java.util.ArrayList;

//Créé par Malcolm Mielle et Mathieu Nassar le 8/03/2013
/**
 * Cette classe abstraite représente les capteurs du robot.
 * Il s'agit d'une vision globale du capteur et des fonctions de base :
 * sa position,
 * la fonction détecter... 
 * Des accesseurs sont disponibles grâce à getPos et setPos pour savoir où se trouve le capteur et pour placer le capteur en un point.
 * @author Malcolm Mielle et Mathieu Nassar
 * @see Point
 */

public abstract class Capteur implements Cloneable{
	//Attributs
	protected Posture pos;
	
	//Constructeurs
	public Capteur(){
		
	}
	
	public Capteur(Posture point){
		this.pos = point;
	}
	
	//Methodes
	/**
	 * Methode abstraite de detection
	 * @param objet
	 * @return int
	 */
	public abstract int detecter( ArrayList<Objet> objet);
	
	/**
	 * Accesseur de la posture
	 * @return Posture
	 */
	public Posture getPos(){
		return this.pos;
	}
	
	/**
	 * Fixe la posture a une nouvelle posture
	 */
	public void setPos(Posture pos_new){
		this.pos=pos_new;
	}
	
	public String toString(){
		return "position en x : " + this.pos.getX() + " position en y : " + this.pos.getY() ;
	}

	public Object clone(){
		Object o=null;
		try{
			o=(Capteur) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
	/**
	 * Retourne le type de capteur
	 * @return String
	 */
	public abstract String type();
	public abstract void paint(MonGraphics g);
}
