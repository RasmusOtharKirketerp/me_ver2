package me_ver2;

import java.awt.Graphics2D;
import java.awt.PointerInfo;
import java.util.ArrayList;

public class Planet {
	// Center SOL
	private String planetName;
	private SolarElement planetSE;
	Kredsløb kredsloeb;
	private int zoom_factor = 8;

	private double distancePrClick; // km/t
	private int planetensStartAfstandKredsløbet = 0;
	private int planetensGradIKredsløbet = 0;

	// Venus Merkur Jorden Mars...
	ArrayList<Planet> planeterIkredsloeb = new ArrayList<Planet>();

	// CONSTRUCTOR ------------------------------------------
	public Planet(String planetName, SolarElement SE) {
		setPlanetName(planetName);
		this.planetSE = SE;
		this.beregnPlanetensGradIKredsløbet(0);
	}
	// Metoder

	public int getPlanetensGradIKredsløbet() {
		return this.planetensGradIKredsløbet;
	}

	public void setPlanetensGradIKredsløbet(int grad) {
		this.planetensGradIKredsløbet = grad;
	}

	public double getDistancePrClick() {
		return distancePrClick;
	}

	public void setDistancePrClick(double fart) {
		this.planetSE.speed = fart;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public void addPlanet(Planet planet, int in_afstandTilCenterPlanet) {
		planet.planetSE.setRadius(in_afstandTilCenterPlanet);
		planeterIkredsloeb.add(planet);
	}

	public double beregnAfstandTilbagelagtIalt(double click) {
		// afstand tilbagelagt i kredsløbet i alt
		double retVal = click * this.getDistancePrClick();
		return retVal;
	}

	public int getPlanetensStartAfstandKredsløbet() {
		return planetensStartAfstandKredsløbet;
	}

	public void setPlanetensStartAfstandKredsløbet(int planetensStartAfstandKredsløbet) {
		this.planetensStartAfstandKredsløbet = planetensStartAfstandKredsløbet;
	}

	public void drawPlanet(Graphics2D g, Screen s, EclipseTime ec) {

		this.beregnPlanetensGradIKredsløbet(ec.getSSClick());
		this.getPlanetX(getPlanetensGradIKredsløbet());
		this.getPlanetY(getPlanetensGradIKredsløbet());
		int r = (int) this.planetSE.getRadius();
		int x = (int) this.planetSE.x - (r / 2);
		int y = (int) this.planetSE.y - (r / 2);

		g.setColor(this.planetSE.color);
		// Paint the Planet
		// g.fillOval(x, y, r, r);
		this.tegnPlanet(g, ec);
		g.drawString(planetName, (int) planetSE.x - (int) planetSE.omkreds, (int) planetSE.y);

		// Paint the Kredsløb it is in...If it has one
		if (this.kredsloeb != null)
			this.kredsloeb.tegn(g);
		// LOOP IS MISSING FOR MORE PLANETS!!!
		if (this.planeterIkredsloeb.size() > 0)
			this.planeterIkredsloeb.get(0).drawPlanet(g, s, ec);
	}

	public void beregnPlanetensGradIKredsløbet(double d) {
		// Denne funktin skal retunere den grad planetet er i kredsløbet
		// ud fra den vinkel en linje skulle tegnes fra centrum og ud
		int retVal = 0;
		double afstand = this.getPlanetensStartAfstandKredsløbet()
				+ this.beregnAfstandTilbagelagtIalt(d) % this.planetSE.getOmkreds();
		retVal = (int) ((afstand / this.planetSE.getOmkreds()) * 360);

		this.planetensGradIKredsløbet = retVal;
	}

	public void LogPlanetXY() {
		System.out.println(this.getPlanetName() + "(X,Y) : " + this.planetSE.x + "," + this.planetSE.y + " Vinkel : "
				+ planetensGradIKredsløbet);
	}

	public double getPlanetX(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelX = Math.cos(radianer);

		this.planetSE.x = (enhedsCirkelX * this.planetSE.getRadius());
		this.planetSE.x += this.planetSE.x;
		return this.planetSE.x;
	}

	public double getPlanetY(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelY = Math.sin(radianer);

		this.planetSE.y = (enhedsCirkelY * (double) this.planetSE.getRadius());
		this.planetSE.y += this.planetSE.y;
		return this.planetSE.getRadius();
	}

	public void tegnPlanet(Graphics2D g, EclipseTime ec) {
		// TODO Auto-generated method stub
		double r = this.planetSE.getRadius() * zoom_factor;
		double x = this.planetSE.x - (r / 2);
		double y = this.planetSE.y - (r / 2);
		// g.setColor(this.getColor());
		g.fillArc((int) (int) x, (int) y, (int) r, (int) r, (int) this.getPlanetensGradIKredsløbet() * -1,
				(int) this.getDistancePrClick());
		// g.drawArc(x, y, r, r, (int) this.planetensPostionIKredsløbet(ec) *
		// -1, (int) this.planet.getDistancePrClick());
		this.getPlanetX(this.getPlanetensGradIKredsløbet());
		this.getPlanetY(this.getPlanetensGradIKredsløbet());
		g.fillOval((int) this.getPlanetX(this.getPlanetensGradIKredsløbet()),
				(int) this.getPlanetY(this.getPlanetensGradIKredsløbet()), 10, 10);
		LogPlanetXY();
	}

}
