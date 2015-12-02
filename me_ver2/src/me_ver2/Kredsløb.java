package me_ver2;
import java.awt.Graphics2D;

public class Kredsløb {
	// http://www.formel-samling.dk/19-formler/matematik-kategorier/geometri/38-cirkel-formler
	// https://www.matematikfessor.dk/lessons/enhedscirklen-cosinus-og-sinus-107
	// Atributter for Kredsløb
	
	private int zoom_factor = 8;
    SolarElement kredloebSE;
	private String navn;

	// Konstructor
	public Kredsløb(SolarElement in_SE, String p, int startAfstand) {
		this.kredloebSE = in_SE;;
	};

	public String getNavn() {
		return navn;
	}

	public void setNavn(String n) {
		this.navn = n;
	}

	public void tegn(Graphics2D g) {
		double r = this.kredloebSE.getRadius() * zoom_factor;
		double x = this.kredloebSE.x - (r / 2);
		double y = this.kredloebSE.y - (r / 2);
		g.drawOval((int)x, (int)y, (int)r, (int)r);
	}
}