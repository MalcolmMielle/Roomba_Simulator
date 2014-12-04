import java.util.ArrayList;

/**
 * Comportement du robot permettant un noettoyage aleatoire de la piece dans laquelle il se trouve.
 * @author Mathieu Nassar et Malcolm Mielle
 * @see Comportement
 *
 */

public class Nettoie extends Comportement {
	// Attributs
	private int flag;
	private int sens;
	private double prev_x;
	private double prev_y;
	
	// Constructeurs
	Nettoie() {
		flag = 0;
		sens = 0;
		prev_x = -1; prev_y = -1;
	}
	
	// Methodes
	/**
	 * Methode appelee par le robot.
	 * Genere un deplacement du robot ayant pour but de nettoyer les taches presentes dans la piece.
	 * @param capteurs la liste des capteurs
	 * @param capteursAns la liste des reponse des capteurs
	 * @return Consigne: consignes des roues donnant lieu au deplacement du robot.
	 */
	public Consigne generique(ArrayList<Capteur> capteurs, int[] capteursAns) {
		double vrobmax = 100; // en cm.s-1
		double tau = 0.01; // pas de temps de 10ms
		int collision = -1;
		int tache = -1;
		Consigne cons = new Consigne();
		
		if (flag <= 0) {
			for (int i=0;i<capteurs.size();i++) { // detection par tous les capteurs
				if (capteurs.get(i).type().equals("Proximite") && capteursAns[i] != -1) {
					collision = i;
				}
				if (capteurs.get(i).type().equals("Salete") && capteursAns[i] != -1)
					tache = capteursAns[i];
			}
			if (tache != -1) { // Si on rencontre une tache, l'unique consigne contient l'indice de cette tache
				cons.addConsigne(tache);
				return cons;
			}
			if (collision != -1) { // Si l'on est en collision on cherche a se retourner
				if (prev_x != capteurs.get(collision).getPos().getX() && prev_y != capteurs.get(collision).getPos().getY()) {
					System.out.println("Collision.");
					System.out.println("Capteur implique :");
					System.out.println(capteurs.get(collision));
					prev_x = capteurs.get(collision).getPos().getX();
					prev_y = capteurs.get(collision).getPos().getY();
				}
				
				flag = (int) (Math.random()*(20-10)+10);
				sens = (int) (Math.random()*2);
				if (sens == 1) { cons.addConsigne(tau*vrobmax); cons.addConsigne(tau*-1*vrobmax); }
				else { cons.addConsigne(-1*tau*vrobmax); cons.addConsigne(tau*vrobmax); }
			}
			else {
				cons.addConsigne(tau*vrobmax);
				cons.addConsigne(tau*vrobmax);
			}
		}
		else {
			if (sens == 1) { cons.addConsigne(tau*vrobmax); cons.addConsigne(tau*-1*vrobmax); }
			else { cons.addConsigne(-1*tau*vrobmax); cons.addConsigne(tau*vrobmax); }
			flag -= 1;
		}
		return cons;
	}
	
	public String toString() {
		return "Nettoie";
	}
	
	public Object clone() {
		Object o=null;
		o=(Nettoie) super.clone();
		return o;
	}
}
