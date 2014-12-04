// Cree par Malcolm Mielle et Mathieu Nassar le 8/03/2013

/** Classe representant une forme rectangulaire.
 * Sera utilisee pour definir des obstacles et des taches.
 * @author Mathieu Nassar et Malcolm Mielle
 * @see Forme
 */

public class Rectangle extends Forme {
	// Attributs
	private int longueur;
	private int largeur;
	
	// Constructeurs
	/** Constructeur de Rectangle a partir d'un point central cen, d'une largeur et d'une longueur.
	 * @param cen Point, 
	 * @param lon int, 
	 * @param lar int
	 */
	Rectangle(Point cen, int lon, int lar) {
		super(cen);
		longueur = lon;
		largeur = lar;
	}
	
	// Methodes
	/** Retourne la longueur du rectangle.
	 * @return longueur
	 */
	public int getLongueur() {
		return longueur;
	}
	
	/** Retourne la largeur du rectangle.
	 * @return largeur
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Retourne vrai si le robot designe par ro dans la posture post est en collision avec le Rectangle.
	 * @param ro Roomba, 
	 * @param post Posture
	 */
	public boolean isColliding(Roomba ro, Posture post) {
		Cercle c = new Cercle(new Point(post.getX(),post.getY()),ro.getDiametre());
		return isColliding(c);
	}
	
	  /**
	   * return l'angle remis dans [0;2PI]
	   * @param a l'angle à normaliser
	   * @return a l'angle normalisé
	   */
	  protected double nangle(double a)
	  {
		  while(a>=(2*Math.PI)){
				a=a-(2*Math.PI);
			}
		  while(a<0){
				a=a+(2*Math.PI);
			}
		  return a;
	  }
	
	/**
	 * Methode detectant la collision d'un capteur de proximité avec un rectangle.
	 * @param cpt : Capteur de collision
	 * @return boolean
	 */
	
	public boolean collide(Capteur_prox cpt){
		Cercle c=new Cercle(new Point(cpt.getPos().getX(), cpt.getPos().getY()), (int) (cpt.getDistance()*2));
		if(this.isColliding(c)){
			Point p = new Point(cpt.getPos().getX(), cpt.getPos().getY());
			Point a = new Point(centre.getX()+(largeur/2),centre.getY()+(longueur/2));
			Point b = new Point(centre.getX()+(largeur/2),centre.getY()-(longueur/2));
			Point d = new Point(centre.getX()-(largeur/2),centre.getY()-(longueur/2));
			Point e = new Point(centre.getX()-(largeur/2),centre.getY()+(longueur/2));
			double angle=cpt.getPos().getTheta();
			//Normalisation de l'angle.
			angle=nangle(angle);
			double angle_plus=angle+cpt.taille_angle/2;
			double angle_moins=angle-cpt.taille_angle/2;	
			angle_moins=nangle(angle_moins);
			angle_plus=nangle(angle_plus);			
			//Premier cas.
			if(p.projectionColliding(b, a) ||p.projectionColliding(e, d) ){
				double d1 = (cpt.getPos().getX()-a.getX())*(cpt.getPos().getX()-a.getX()) + (cpt.getPos().getY()-a.getY())*(cpt.getPos().getY()-a.getY());
				double d2 = (cpt.getPos().getX()-e.getX())*(cpt.getPos().getX()-e.getX()) + (cpt.getPos().getY()-e.getY())*(cpt.getPos().getY()-e.getY());
				
				//On tape le cote a
				if(d1<=d2){		
					if(angle_plus>=angle_moins){
						return (Math.PI<=(angle_plus) && Math.PI>=(angle_moins));
					}
					else{
						return(Math.PI<=(angle_plus) || Math.PI>=(angle_moins));
					}
				}
				else{
					if(angle_plus>=angle_moins){
						return (0<=(angle_plus) && 0>=(angle_moins));
					}
					else{
						return (0<=(angle_plus) || 0>=(angle_moins));			
					}
				}
			}
				//Deuxieme cas.
			if( p.projectionColliding(b, d) || p.projectionColliding(e, a)){	
				double d1 = (cpt.getPos().getX()-a.getX())*(cpt.getPos().getX()-a.getX()) + (cpt.getPos().getY()-a.getY())*(cpt.getPos().getY()-a.getY());
				double d2 = (cpt.getPos().getX()-b.getX())*(cpt.getPos().getX()-b.getX()) + (cpt.getPos().getY()-b.getY())*(cpt.getPos().getY()-b.getY());	
				if(d1>=d2){	
					if(angle_plus>=angle_moins){
						return (Math.PI/2<=(angle_plus) && Math.PI/2>=(angle_moins));
					}
					else{
						return(Math.PI/2<=(angle_plus) || Math.PI/2>=(angle_moins));
					}
				}
				else{
					if(angle_plus>=angle_moins){
						return ((3*Math.PI)/2<=(angle_plus) && (3*Math.PI)/2>=(angle_moins));
					}
					else{
						return((3*Math.PI)/2<=(angle_plus) || (3*Math.PI)/2>=(angle_moins));
					}
				}
			}
			//Cas du coin...
			else{
				double d_1 = ((a.getX()-cpt.getPos().getX())*(a.getX()-cpt.getPos().getX()))+((a.getY()-cpt.getPos().getY())*(a.getY()-cpt.getPos().getY()));
				double d_2 = ((b.getX()-cpt.getPos().getX())*(b.getX()-cpt.getPos().getX()))+((b.getY()-cpt.getPos().getY())*(b.getY()-cpt.getPos().getY()));
				double d_3 = ((d.getX()-cpt.getPos().getX())*(d.getX()-cpt.getPos().getX()))+((d.getY()-cpt.getPos().getY())*(d.getY()-cpt.getPos().getY()));
				double d_4 = ((e.getX()-cpt.getPos().getX())*(e.getX()-cpt.getPos().getX()))+((e.getY()-cpt.getPos().getY())*(e.getY()-cpt.getPos().getY()));				
				double min;
				double flag=0;	
				double angl_test;
				
				if(d_1<d_2 && d_1<d_3 && d_1<d_4){
					min=d_1;
					flag=a.getX()-cpt.getPos().getX();
					min=Math.sqrt(min);
					angl_test=Math.acos(flag/min);					
					angl_test=nangle(angl_test);				
					angl_test=-angl_test;
					angl_test=nangle(angl_test);
				
				}
				else if(d_2<d_3 && d_2<d_4){
					min=d_2;
					flag=b.getX()-cpt.getPos().getX();
					min=Math.sqrt(min);
					angl_test=Math.acos(flag/min);
					angl_test=nangle(angl_test);

				}
				else if(d_3<d_4){
					min=d_3;
					flag=d.getX()-cpt.getPos().getX();
					min=Math.sqrt(min);
					angl_test=Math.acos(flag/min);
					angl_test=nangle(angl_test);

				}
				else{
					min=d_4;
					flag=e.getX()-cpt.getPos().getX();
					min=Math.sqrt(min);
					angl_test=Math.acos(flag/min);
					angl_test=nangle(angl_test);
					angl_test=-angl_test;
					angl_test=nangle(angl_test);

				}
				if(angle_plus>angle_moins){
					return (angl_test<=angle_plus && angl_test>=angle_moins);
				}
				else{
					return (angl_test<=angle_plus || angl_test>=angle_moins);
				}			
			}
		}
		return false;
	}
	
	/**
	 * Fonction de collision entre un rectangle et un capteur de saleté.
	 * @param cap
	 * @return boolean
	 */
	public boolean collide(Capteur_sal cap){
		Cercle c = new Cercle(new Point(cap.getPos().getX(),cap.getPos().getY()),5);
		return isColliding(c);
	}
			
	/**
	 * Test la collision entre le rectangle et un Cercle c
	 * @param c Cercle dont on souhaite etudier la collision
	 * @return true si le rectangle et le cercle sont en collision
	 */
	public boolean isColliding(Cercle c) {
		double x = c.getCentre().getX();
		double y = c.getCentre().getY();
		
		Rectangle bounding = new Rectangle(new Point(x,y),(int)c.getDiametre(),(int)c.getDiametre());
		if (!this.isColliding(bounding))
			return false;
		Cercle rob = new Cercle(new Point(x, y),c.getDiametre());
		if (rob.isColliding(new Point(centre.getX()-largeur/2,centre.getY()-longueur/2)) ||
			rob.isColliding(new Point(centre.getX()+largeur/2,centre.getY()-longueur/2)) ||
			rob.isColliding(new Point(centre.getX()-largeur/2,centre.getY()+longueur/2)) ||
			rob.isColliding(new Point(centre.getX()+largeur/2,centre.getY()+longueur/2)))
			return true;
		if (this.isColliding(rob.getCentre()))
			return true;
		return rob.getCentre().projectionColliding(new Point(centre.getX()-largeur/2,centre.getY()-longueur/2),
									new Point(centre.getX()-largeur/2, centre.getY()+longueur/2)) ||
			   rob.getCentre().projectionColliding(new Point(centre.getX()-largeur/2,centre.getY()+longueur/2),
									new Point(centre.getX()+largeur/2, centre.getY()+longueur/2));
	}
	
	/**
	 * Retourne vrai si ce Rectangle est en collision avec le Rectangle rect
	 * @param rect
	 * @return boolean
	 */
	public boolean isColliding(Rectangle rect) {
		return !(rect.getCentre().getX()-rect.getLargeur()/2 > centre.getX()+largeur/2 ||
				rect.getCentre().getX()+rect.getLargeur()/2 < centre.getX()-largeur/2 ||
				rect.getCentre().getY()-rect.getLongueur()/2 > centre.getY()+longueur/2 ||
				rect.getCentre().getY()+rect.getLongueur()/2 < centre.getY()-longueur/2);
	}
	
	/**
	 * Retourne vrai si le point p est en collision ou a l'interieur du Rectangle
	 * @param p
	 * @return boolean
	 */
	public boolean isColliding(Point p) {
		return (p.getX() >= centre.getX()-largeur/2 &&
				p.getX() <= centre.getX()+largeur/2 &&
				p.getY() >= centre.getY()-longueur/2 &&
				p.getY() <= centre.getY()+longueur/2);
	}
	
	/**
	 * Retourne une taille reduite a un entier dans le but d'etre comparee a d'autres formes.
	 */
	public int getHomogeneousSize() {
		return Math.min(largeur, longueur);
	}
	
	/**
	 * Reduit la taille de la forme d'un minimum de 10%.
	 */
	public void reduire() {
		double d = longueur*10/100;
		double d2 = largeur*10/100;
		if (d < 5) d=5;
		if (d2 <5) d2=5;
		longueur -= d;
		largeur -= d2;
		if (longueur < 0) longueur = 1;
		if (largeur < 0) largeur = 1;
	}
	
	public String toString() {
		return("Rectangle - Centre: (" +centre.getX()+ " ; " +centre.getY()+ ") - longueur: "+longueur+ " - largeur: "+largeur);
	}
	
	public void paint(MonGraphics g) {
		g.draw(this);
	}
	
	public Object clone() {
		Object o=null;
		o=(Rectangle) super.clone();
		return o;
	}
}
