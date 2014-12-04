/**
 * Classe implementant une roue.
 * @author Malcolm Mielle et Mathieu Nassar
 * @see Robot
 *
 */
public class Roue implements Cloneable {
	private double distance;
	
	//Constructeurs
	public Roue(){
		this.distance=0;
	}
	
	//Methodes
	public void setDistance(double distance){
		this.distance=distance;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public Object clone() {
		Object o = null;
		try {
			o=(Roue) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		}
		return o;
	}

}
