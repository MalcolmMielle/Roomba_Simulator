import java.util.ArrayList;

//Créé par Malcolm Mielle et Mathieu Nassar le 8/03/2013
/**
 * La classe définissant un capteur de proximité.
 * Le capteur de proximité peut être placé à une position (x,y)
 * @author Malcolm Mielle et Mathieu Nassar
 * @see Capteur
 */
public class Capteur_prox extends Capteur {
	
	//Attributs
	protected double distance;
	protected double taille_angle;
	//Constructeur
	public Capteur_prox(){
		super();
		this.distance= 0;
		this.taille_angle=0;
	}
	
	public Capteur_prox(Posture pos, double d, double t){
		super(pos);
		this.distance=d;
		this.taille_angle=t;
		
	}
	
	/**
	 * Methode permettant de savoir si le capteur est en contact avec un objet.
	 * Retourne l'indice de l'objet avec lequel le capteur est en collision.
	 * @param objet une liste d'Objet
	 * @return int l'indice de l'objet en collision ou -1
	 */
	public int detecter(ArrayList<Objet> objet){
		int size=objet.size();
		for (int i=0; i<size; i++)
		{
			if(objet.get(i).isCleanable() == false)
			{
				Forme f = objet.get(i).getForme();
				boolean bool = f.collide(this);
				if(bool){
					return i;
				}
			}
		}
		return -1;
		
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getAngle(){
		return this.taille_angle;
	}
	
	/**
	 * Fonction de retour de type de capteur
	 * @return String
	 */
	public String type(){
		return "Proximite";
	}
	
	public String toString(){
		return "Capteur de proximite ("+ (int)this.pos.getX() +" ; "+ (int)this.pos.getY() +")";
	}
	
	public void paint(MonGraphics g){
		g.draw(this);
	}
	
}
