import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.Test;


public class MonGraphics2DTest {

	@Test
	public void test() {
		System.out.println("BOU\n");
		JFrame ma_fenetre = new JFrame("Cercle rouge");
	    MonGraphics2D m = new MonGraphics2D();
	    m.setPreferredSize(new Dimension(400, 400));
	    ma_fenetre.setContentPane(m);
	    ma_fenetre.pack();
	    ma_fenetre.setVisible(true);
	    
	    ArrayList<Capteur> mc =new ArrayList<Capteur>();
	    Capteur_sal cap = new Capteur_sal(new Posture(30,150,0));
		mc.add(cap);
		Roue r1, r2;
		r1= new Roue();
		r2= new Roue();
		Posture post = new Posture(100,100,0);
		Roomba rob= new Roomba(r1, r2, mc, post, new Nettoie(), 30);
	    
		Piece pi = new Piece(350,350);
		pi.addRobot(rob);
	    m.setPiece(pi);
	    
	    
	    while (true)
	      {
	    	pi.animate();
		// attend 0.01 sec
		try  { Thread.sleep(10); }
		catch (Exception e) {}
		// redessine (appelle entre autres paint())
		m.repaint();
	      }
	}

}
