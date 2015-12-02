package me_ver2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	public Main() {
	}

	private static final long serialVersionUID = 1L;
	static EclipseTime ec = new EclipseTime();
	static Screen s = new Screen(800, 800);

	// SUN SUN SUN
	static SolarElement sol_se       = new SolarElement(s.getCenterX(), s.getCenterY(), Color.RED, 0, 6);
	static Planet voresSol           = new Planet("Sol", sol_se);

	static SolarElement jorden_se             = new SolarElement((int) sol_se.x, (int) sol_se.y, Color.green, 30, 40);
	static SolarElement jordenkredsloeb_se    = new SolarElement((int) sol_se.x, (int) sol_se.y, Color.green, 30, 40);
	static Planet jorden                      = new Planet("Jorden", jorden_se);

	static SolarElement mars_se               = new SolarElement((int) sol_se.x, (int) sol_se.y, Color.blue, 30, 40);
	static SolarElement marskredsloeb_se      = new SolarElement((int) sol_se.x, (int) sol_se.y, Color.blue, 30, 40);
	static Planet mars                        = new Planet("Mars", jorden_se);

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		voresSol.drawPlanet(g2d, s, ec);
		g.drawLine(1, 1, s.getCenterX(), s.getCenterY());
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		// initier univers
		// ------------------------------------------------------------------------
		// Planet voresSol = new Planet("Sol");

		JFrame frame = new JFrame("MultiEclipse");
		Main universe = new Main();
		frame.add(universe);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		voresSol.setDistancePrClick(0);

		jorden.setDistancePrClick(10);

		voresSol.addPlanet(jorden, 100);
		voresSol.addPlanet(mars, 300);

		jorden.kredsloeb = new Kredsløb(jordenkredsloeb_se, "Jorden", 0);
		mars.kredsloeb   = new Kredsløb(marskredsloeb_se, "Jorden", 0);

		while (true) {

			universe.repaint();

			Thread.sleep(400);

			ec.click();
			System.out.print(" @" + ec.getSSClick());

		}
	}

}
