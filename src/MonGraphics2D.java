import java.awt.*;
import javax.swing.*;

/**
 * Classe permettant le dessin en 2D des éléments de la scène.
 * La scène contient des objets (taches ou non) ainsi qu'un robot.
 * Les objets sont ici soit circulaires, soit rectangulaires.
 * @author Malcolm Mielle et Mathieu Nassar
 *
 */

public class MonGraphics2D extends JPanel implements MonGraphics {

	/**
	 * 
	 */
	// Attributs :
	private Graphics2D g2;
	private Piece piece;
	
	//Constructeur
	public MonGraphics2D(){
	    setBackground(Color.black);
	    setOpaque(true);
	    //A changer pour la piece
	    piece=new Piece();
	}
	
	public MonGraphics2D(Piece pi){
	    setBackground(Color.black);
	    setOpaque(true);
	    //A changer pour la piece
	    piece=pi;	
	}
	
	public void setPiece(Piece m) {
		piece = m;
	}
	/**
	 * Foction de test
	 */
	
	public void paint(Graphics g){
		super.paint(g);
		this.g2= (Graphics2D) g;
		
		piece.paint(this);
		g2.dispose();
	}
	
	
	/**
	 * Dessine Roomba comme un cercle bleu.
	 */
	public void draw(Roomba ro) {
		g2.setColor(Color.white);
		g2.fillOval((int)ro.getPosture().getX()-ro.getDiametre()/2, (int)ro.getPosture().getY()-ro.getDiametre()/2, ro.getDiametre(), ro.getDiametre());
		g2.setColor(Color.blue);
		g2.fillOval((int)ro.getPosture().getX()-ro.getDiametre()/2+1, (int)ro.getPosture().getY()-ro.getDiametre()/2+1, ro.getDiametre()-2, ro.getDiametre()-2);
		//ro.setPostureA(ro.getPosture());
		this.g2.setColor(Color.white);
		g2.drawLine((int)ro.getPosture().getX(),
				(int)ro.getPosture().getY(),
				(int)(ro.getPosture().getX()+ro.getDiametre()/2*Math.cos(ro.getPosture().getTheta())),
				(int)(ro.getPosture().getY()+ro.getDiametre()/2*Math.sin(ro.getPosture().getTheta())));
	}
	/**
	 * Dessine le capteur de salete comme un cercle au centre du robot.
	 */
	public void draw(Capteur_sal cap) {
		g2.fillOval((int)(cap.getPos().getX()-2.5), (int)(cap.getPos().getY()-2.5), 5, 5);
	}
	
	/**
	 * Dessine les capteurs de proximite comme des lignes representant l'angle median de leur zone de detection
	 * Leur longueur est celle des capteurs.
	 */
	public void draw(Capteur_prox cap) {
		this.g2.setColor(Color.red);
		g2.drawLine((int)cap.getPos().getX(),
				(int)cap.getPos().getY(),
				(int)(cap.getPos().getX()+(cap.getDistance()*Math.cos(cap.getPos().getTheta()))),
				(int)(cap.getPos().getY()+(cap.getDistance()*Math.sin(cap.getPos().getTheta()))));
	}
	
	
	public void draw(Cercle c) {
		g2.fillOval((int)c.getCentre().getX()-c.getDiametre()/2, (int)c.getCentre().getY()-c.getDiametre()/2, c.getDiametre(), c.getDiametre());
	}

	public void draw(Rectangle r){
		g2.fillRect((int)r.getCentre().getX()-r.getLargeur()/2, (int)r.getCentre().getY()-r.getLongueur()/2, r.getLargeur(), r.getLongueur());
	}
	
	public void draw(Tache tache){
		this.g2.setColor(Color.orange);
		tache.getForme().paint(this);
	}
	
	public void draw(Objet obj){
		this.g2.setColor(Color.green);
		obj.getForme().paint(this);
	}
	
	public void draw(Piece piece){
	}

}
