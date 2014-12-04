// Cr�� par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe representant une forme circulaire.
 * Sera utilisee pour definir des obstacles et des taches.
 * @author Mathieu Nassar et Malcolm Mielle
 * @see Forme
 */

public class Cercle extends Forme {
	// Attributs
	private int diametre;
	
	// Constructeurs
	Cercle() {
		super(new Point(1,1));
		diametre = 1;
	}
	/** Constructeur de Cercle a partir d'un point central cen, et d'un diametre d.
	 * @param cen
	 * @param d
	 */
	Cercle(Point cen, int d) {
		super(cen);
		diametre = d;
	}
	
	// Methodes
	/** Retourne le diametre du cercle.
	 * @return diametre
	 */
	public int getDiametre() {
		return diametre;
	}
	
	public void setDiametre(int d) {
		diametre = d;
	}
	
	/**
	 * Methode de collision avec un Roomba
	 * @param ro le Roomba
	 * @param post la Posture
	 * @return boolean
	 */
	public boolean isColliding(Roomba ro, Posture post) {
		double xrob = post.getX();
		double yrob = post.getY();
		return Math.hypot(xrob-centre.getX(),yrob-centre.getY()) <= (ro.getDiametre()+diametre)/2;
	}
	
	/**
	 * Methode de collision avec un capteur de proximite
	 * @param cpt le Capteur de proximite
	 * @return boolean
	 */
	
	public boolean collide(Capteur_prox cpt){
		double distance=( (centre.getX()-cpt.getPos().getX())*(centre.getX()-cpt.getPos().getX()) ) + ( (centre.getY()-cpt.getPos().getY()) * (centre.getY()-cpt.getPos().getY()));
		if (distance > ( ( (diametre/2) + cpt.getDistance())*( (diametre/2)+cpt.getDistance()) )){
			return false;
		}
		else{
			double dist_reel=Math.sqrt(distance);
			//Cercle moins le capteur
			double angle = Math.acos((centre.getX()-cpt.getPos().getX())/dist_reel);
			double angle2 = Math.asin((centre.getY()-cpt.getPos().getY())/dist_reel);
			if(centre.getY()<cpt.getPos().getY()){
				angle=-angle;
			}
			if(angle>=(2*Math.PI)){
				angle=angle-(2*Math.PI);
			}
			if(angle<0){
				angle=angle+(2*Math.PI);
			}
			if(angle2>=(2*Math.PI)){
				angle2=angle2-(2*Math.PI);
			}
			if(angle2<0){
				angle2=angle2+(2*Math.PI);
			}
			
			
			double t1=cpt.getPos().getTheta()-(cpt.getAngle()/2);
			double t2=(cpt.getPos().getTheta()+(cpt.getAngle()/2));
			if(t1>=2*Math.PI){
				t1=t1-(2*Math.PI);
			}
			if(t1<0){
				t1=t1+(2*Math.PI);
			}
			if(t2>=2*Math.PI){
				t2=t2-(2*Math.PI);
			}
			if(t2<0){
				t2=t2+(2*Math.PI);
			}
			
			if(t2<t1){
				if(angle>=t1 || angle <=t2){
					return true;
				}
				else{
					return false;
				}
			}
			else{

				if(angle>=t1 && angle <=t2)
					return true;
				else{
					return false;
				}
			}
		}
	}
	
	/**
	 * Teste si le point designe par p est en collision avec le cercle.
	 * @param p point a tester
	 * @return true si p est en collision avec le cercle.
	 */
	public boolean isColliding(Point p) {
		return Math.hypot(p.getX()-centre.getX(),p.getY()-centre.getY()) <= diametre/2;
	}
	
	/**
	 * Retourne une taille reduite a un entier dans le but d'etre comparee a d'autres formes.
	 */
	public int getHomogeneousSize() {
		return diametre;
	}
	
	/**
	 * Reduit la taille de la forme d'un minimum de 10%.
	 */
	public void reduire() {
		double d = diametre*10/100;
		if (d < 5)
			d=5;
		diametre -= d;
		if (diametre<0)
			diametre = 1;
	}
	
	public String toString() {
		return("Cercle - Centre: (" +centre.getX()+ " ; " +centre.getY()+ ") - diametre: "+diametre);
	}
	
	public void paint(MonGraphics g) {
		g.draw(this);
	}
	
	public Object clone() {
		Object o=null;
		o=(Cercle) super.clone();
		return o;
	}
}
