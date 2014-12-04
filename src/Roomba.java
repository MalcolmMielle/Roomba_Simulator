
import java.util.ArrayList;
/**
 * Classe du Robot Roomba. 
 * Etant un robot, il possede tous les attributs de cette classe. A ceci s'ajoute un diametre qui definit le robot comme etant un roomba
 * Le roomba peut se deplacer, et appeler ses capteurs pour reagir a son environnement.
 * @author Malcolm Mielle et Mathieu Nassar.
 *
 */

public class Roomba extends Robot {

	private int diametre;
	
	//constructeur.
	public Roomba(){
		super();
		this.diametre=0;
	}
	public Roomba(Roue r1, Roue r2, ArrayList<Capteur> capt, Posture post, Comportement comp, int diam){
		super(r1, r2, capt, post, comp);
		this.diametre=diam;
	}
	
	//Methodes
	public int getDiametre(){
		return this.diametre;
	}
	
	public void setDiametre(int d){
		this.diametre=d;
	}
	
	/**
	 * Methode permettant de deplace le Roomba dans son environnement.
	 * Le robot se sert de la distance parcourue par les roues pour avancer.
	 * Les distances sont ensuite remise a zero.
	 * @param p la Posture de depart
	 */
	public void deplace(Posture p){
		Posture p_a = new Posture (post.getX(), post.getY(), post.getTheta());
		post = p;
		double dangle = p.getTheta()-p_a.getTheta();
		if(dangle>=2*Math.PI)
			dangle=dangle-2*Math.PI;
		if(dangle<0)
			dangle=dangle+2*Math.PI;
			
		for(int i=0;i<capteur.size();i++) {
			capteur.get(i).getPos().setX(p.getX());
			capteur.get(i).getPos().setY(p.getY());
			capteur.get(i).getPos().setTheta(capteur.get(i).getPos().getTheta()+dangle);
		}
		this.roue.get(0).setDistance(0);
		this.roue.get(1).setDistance(0);
	}
	
	/**
	 * Calcule une nouvelle position a l'aide du comportement du robot.
	 * Cette nouvelle posture sera soumise a validation par la piece contenante avant le deplacement.
	 * @param objets la liste des Objets de la piece
	 * @return decision : la posture calculee par le comportement.
	 */
	public Posture calculerPosition(ArrayList<Objet> objets) {
		Consigne cons = comp.generique(capteur, getCapteurAnswer(objets));
		if (cons.size() == 1) {
			nettoyerTache(objets, (int)cons.getConsigne(0));
			return post;
		}
		else {
			roue.get(0).setDistance(cons.getConsigne(0));
			roue.get(1).setDistance(cons.getConsigne(1));
			
			Posture decision = (Posture) post.clone();
			decision.move(roue.get(0).getDistance(), roue.get(1).getDistance(), diametre);
			return decision;
		}
	}
	
	/**
	 * Fonction permettant de nettoyer une tache de la piece.
	 * @param objets la liste des objets
	 * @param ind l'indice de l'objet a nettoyer.
	 */
	public void nettoyerTache(ArrayList<Objet> objets, int ind) {
		if (objets.get(ind).isCleanable()) {
			Tache t = (Tache) objets.get(ind);
			int netlevel = t.getNetLevel();
			if (netlevel > 25) {
				t.nettoyer();
				System.out.println("Nettoyage ...");
			}
			else {
				System.out.println("Tache nettoyee :");
				System.out.println(objets.get(ind));
				objets.remove(ind);
			}
		}
	}
	
	public String toString(){
		return super.toString() + " son diametre est " + this.diametre;
	}
	
	public void paint(MonGraphics g){
		g.draw(this);
		for (int i=0;i<capteur.size();i++) {
			if (capteur.get(i).type().equals("Proximite"))
				capteur.get(i).paint(g);
		}
	}
	
	public Object clone(){
		Object o=null;
		o=(Roomba) super.clone();
		return o;
	}
}
