/**
 * Classe abstraite representant les fonctions de dessin des differents MonGraphics.
 * @author Malcolm Mielle et Mathieu Nassar
 * @see MonGraphics2D
 */

public interface MonGraphics {
	public void draw(Roomba ro);
	public void draw(Cercle c);
	public void draw(Piece piece);
	public void draw(Rectangle rec);
	public void draw(Tache tache);
	public void draw(Objet obj);
	public void draw (Capteur_sal cap);
	public void draw (Capteur_prox cap);
}
