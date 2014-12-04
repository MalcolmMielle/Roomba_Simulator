import java.util.ArrayList;


//Créé par Malcolm Mielle et Mathieu Nassar le 8/03/2013
/**
 * 
 * La classe definissant un capteur de proximite.
 * Le capteur de proximite peut être place a une position (x,y)
 * @author Malcolm Mielle et Mathieu Nassar
 * @see Capteur
 */
public class Capteur_sal extends Capteur implements Cloneable {
	
	//Attributs
	
	//Constructeur
	public Capteur_sal(){
		super();		
	}
	
	public Capteur_sal(Posture pos){
		super(pos);
	}

	/**
	 * Methode de detection de la saleté.
	 * Retourne l'indice de la tache detecter dans le tableau d'bjet passe en argument.
	 * @param objet ArrayList<Objet> une liste d'objet
	 * @return int l'indice de l'objet detecter ou -1
	 */
	
	public int detecter(ArrayList<Objet> objet){
		
		int size=objet.size();
		
		for (int i=0; i<size; i++)
		{
			if(objet.get(i).isCleanable() == true)
			{
				Forme f = objet.get(i).getForme();
				boolean bool = this.collideC((Cercle) f);
				if(bool==true){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Fonction permettant de savoir si le capteur est sur une tache. Le diamètre de vision du capteur est de 5 cm.
	 * @param c Cercle
	 * @return boolean
	 */
	public boolean collideC(Cercle c){
		double distance=( (c.getCentre().getX()-this.getPos().getX())*(c.getCentre().getX()-this.getPos().getX()) ) + ( (c.getCentre().getY()-this.getPos().getY()) * (c.getCentre().getY()-this.getPos().getY()));
		if (distance > ( ( (c.getDiametre()/2) + 2.5)*( (c.getDiametre()/2)+2.5) )){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Fonction de retour de type de capteur
	 * @return String
	 */
	public String type(){
		return "Salete";
	}
	
	public String toString(){
		return "position en x"+ this.pos.getX() +" position en y : "+ this.pos.getY();
	}

	public void paint(MonGraphics g){
		g.draw(this);
	}

	public Object clone() {
		Capteur_sal o=null;
		o=(Capteur_sal) super.clone();
		o.pos = (Posture) pos.clone();
		return o;
	}
	
	
}
