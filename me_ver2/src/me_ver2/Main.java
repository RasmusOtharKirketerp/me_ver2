package me_ver2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	public Main() {	}
	private static final long serialVersionUID = 1L;
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Screen s = new Screen(400,400); 

		// SUN SUN SUN
		SolarElement sol_se    = new SolarElement(s.getCenterX(), s.getCenterY(),Color.YELLOW , 200 , 60);
		Planet voresSol        = new Planet("Sol", sol_se);
		
		SolarElement jorden_se = new SolarElement(0, 0, Color.green, 30, 40);
		Planet jorden          = new Planet("Jorden",jorden_se);
		voresSol.addPlanet(jorden, 100);
		voresSol.drawPlanet(g2d,s);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		// initier univers
		// ------------------------------------------------------------------------
		//Planet voresSol = new Planet("Sol");
			
		JFrame frame = new JFrame("MultiEclipse");
		Main universe = new Main();
		frame.add(universe);
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		universe.repaint();
	}
	
}
