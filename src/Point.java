//Créé par Malcolm Mielle et Mathieu Nassar le 8/03/2013
/**
 * 
 *
 * La classe définissant un point double de l'espace 2D.
 * @author Malcolm Mielle et Mathieu Nassar
 *
 */

public class Point implements Cloneable{
	
	//Attributs
	private double x;
	private double y;
	
	//Constructeurs
	public Point(){
		this.x=0;
		this.y=0;
	}
	
	public Point(double xx, double yy){
		this.x=xx;
		this.y=yy;
	}
	
	//Methodes
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}
	/**
	 * Fonction de collision entre un point et un segment.
	 * @param a premier point du segment
	 * @param b deuxieme point du segment
	 * @return boolean
	 */
	public boolean projectionColliding(Point a, Point b) {
		double prod1 = (x-a.getX())*(b.getX()-a.getX()) + (y-a.getY())*(b.getY()-a.getY());
		double prod2 = (x-b.getX())*(b.getX()-a.getX()) + (y-b.getY())*(b.getY()-a.getY());
		return (prod1*prod2 <= 0);
	}
	
	public String toString(){
		return "position x : "+ this.x + "position y : "+ this.y ;
	}
	
	public Object clone(){
		Object o=null;
		try{
			o= (Point) super.clone();
		}catch(CloneNotSupportedException cnse){
			cnse.printStackTrace(System.err);
		} 
		return o;
	}

}
