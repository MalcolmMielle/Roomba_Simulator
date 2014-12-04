/**
 * La classe Consigne contient les consignes des elements moteurs du robot.
 * Pour un Roomba, les consignes correspondent a la distance a parcourir pour chaque roue.
 * Les consignes sont calculees par les comportements.
 * @author Mathieu Nassar et Malcolm Mielle
 * @see Comportement, Roue, Roomba, Robot
 */

import java.util.ArrayList;

public class Consigne implements Cloneable{
	// Attributs
	private ArrayList<Double> consignes;
	
	// Constructeurs
	Consigne() {
		consignes = new ArrayList<Double>();
	}
	
	Consigne(ArrayList<Double> cons) {
		consignes = cons;
	}
	
	// M�thodes
	/**
	 * Renvoie la taille de la consigne.
	 * @return size: taille de la consigne.
	 */
	public int size() {
		return consignes.size();
	}
	
	/**
	 * Retourne l'�l�ment i converti en entier. 
	 * @param i indice de l'element a obtenir
	 */
	public double getConsigne(int i) {
		return consignes.get(i).doubleValue();
	}
	
	/**
	 * Ajoute une consigne � la liste.
	 * En general, une consigne correspondra a une distance a parcourir par une roue
	 * pour le pas de temps considere.
	 * @param c consigne a entrer.
	 */
	public void addConsigne(double c) {
		consignes.add(new Double(c));
	}
	
	public void removeConsigne(int ind) {
		consignes.remove(ind);
	}
	
	public String toString() {
		String res = "Consigne: \n";
		for (int i=0; i<consignes.size();i++) {
			res = res + consignes.get(i).doubleValue() + "\n";
		}
		return res;
	}
	
	public Object clone() {
		Object o = null;
		try {
			o=(Consigne) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}
}
