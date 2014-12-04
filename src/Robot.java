import java.util.ArrayList;
/**
 * Classe représentant un robot.
 * La classe est abstraite car on ne peut définir un robot sans savoir ce qu'il est (Roomba, ABB...?) Cependant, ils possèdent tous des caractéristiques communes.
 * Ainsi un Robot possède ici des roues pour se déplacer, une position dans l'espace, un comportement et un liste de capteurs intégrés.
 * @author Malcolm Mielle et Mathieu Nassar
 *
 */

public abstract class Robot implements Cloneable {

	//Attributs
	protected ArrayList<Roue> roue;
	protected ArrayList <Capteur> capteur;
	protected Posture post;
	protected Posture post_ante;
	protected Comportement comp;
	
	//Constructeurs.
	public Robot(){
		this.post=new Posture(0,0,0);
		this.post_ante=new Posture(0,0,0);
	};
	
	public Robot(Roue r1, Roue r2, ArrayList<Capteur> capt, Posture post, Comportement comp){
		this.roue = new ArrayList<Roue>();
		this.roue.add(r1);
		this.roue.add(r2);
		
		this.capteur=capt;
		this.post=post;
		this.post_ante=new Posture(post.getX(),post.getY(),post.getTheta());
		this.comp=comp;
	}
	
	//Methodes.
	public abstract void deplace(Posture p);
	public abstract Posture calculerPosition(ArrayList<Objet> objets);
	
	/**
	 * Retourne un tableau contenant les reponses des capteurs
	 * @param objet la liste des objet
	 * @return int[]
	 */
	public int[] getCapteurAnswer(ArrayList<Objet> objet){
		int size=this.capteur.size();
		int[] answer = new int[size];
		for(int i=0;i<size;i++){
			answer[i]=this.capteur.get(i).detecter(objet);
		}
		return answer;		
	}
	
	public int getCapteur(Capteur capteur){
		int size=this.capteur.size();
		for(int i=0;i<size;i++){
			if(this.capteur.get(i).equals(capteur)){
				return i;
			}
		}
		return -1;
	}
	
	public void addCapteur(Capteur capteur){
		this.capteur.add(capteur);
	}
	
	public void removeCapteur(Capteur capteur){
		int i= this.getCapteur(capteur);
		this.capteur.remove(i);
	}
	
	public void setComport(Comportement comp){
		this.comp=comp;
	}
	
	public Comportement getComport(){
		return this.comp;
	}
	
	public void setPosture(Posture post){
		this.post=post;
	}
	
	public Posture getPosture(){
		return this.post;
	}
	
	public void setPostureA(Posture post){
		this.post_ante=post;
	}
	
	public Posture getPostureA(){
		return this.post_ante;
	}
	
	public String toString(){
		return "Robot en posture " + this.post.toString() + " et le comportement " + this.comp.toString();
	}

	public abstract void paint(MonGraphics g);
	
	public Object clone(){
		Object o=null;
		try{
			o=(Robot) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
	
}
