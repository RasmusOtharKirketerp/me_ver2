package me_ver2;

import java.awt.Graphics2D;
import java.awt.PointerInfo;
import java.util.ArrayList;

public class Planet {
	// Center SOL
	private String planetName;
	private SolarElement planetSE;
	Kredsl�b kredsloeb;
	private int zoom_factor = 8;

	private double distancePrClick; // km/t
	private int planetensStartAfstandKredsl�bet = 0;
	private int planetensGradIKredsl�bet = 0;

	// Venus Merkur Jorden Mars...
	ArrayList<Planet> planeterIkredsloeb = new ArrayList<Planet>();

	// CONSTRUCTOR ------------------------------------------
	public Planet(String planetName, SolarElement SE) {
		setPlanetName(planetName);
		this.planetSE = SE;
		this.beregnPlanetensGradIKredsl�bet(0);
	}
	// Metoder

	public int getPlanetensGradIKredsl�bet() {
		return this.planetensGradIKredsl�bet;
	}

	public void setPlanetensGradIKredsl�bet(int grad) {
		this.planetensGradIKredsl�bet = grad;
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
		// afstand tilbagelagt i kredsl�bet i alt
		double retVal = click * this.getDistancePrClick();
		return retVal;
	}

	public int getPlanetensStartAfstandKredsl�bet() {
		return planetensStartAfstandKredsl�bet;
	}

	public void setPlanetensStartAfstandKredsl�bet(int planetensStartAfstandKredsl�bet) {
		this.planetensStartAfstandKredsl�bet = planetensStartAfstandKredsl�bet;
	}

	public void drawPlanet(Graphics2D g, Screen s, EclipseTime ec) {

		this.beregnPlanetensGradIKredsl�bet(ec.getSSClick());
		this.getPlanetX(getPlanetensGradIKredsl�bet());
		this.getPlanetY(getPlanetensGradIKredsl�bet());
		int r = (int) this.planetSE.getRadius();
		int x = (int) this.planetSE.x - (r / 2);
		int y = (int) this.planetSE.y - (r / 2);

		g.setColor(this.planetSE.color);
		// Paint the Planet
		// g.fillOval(x, y, r, r);
		this.tegnPlanet(g, ec);
		g.drawString(planetName, (int) planetSE.x - (int) planetSE.omkreds, (int) planetSE.y);

		// Paint the Kredsl�b it is in...If it has one
		if (this.kredsloeb != null)
			this.kredsloeb.tegn(g);
		// LOOP IS MISSING FOR MORE PLANETS!!!
		if (this.planeterIkredsloeb.size() > 0)
			this.planeterIkredsloeb.get(0).drawPlanet(g, s, ec);
	}

	public void beregnPlanetensGradIKredsl�bet(double d) {
		// Denne funktin skal retunere den grad planetet er i kredsl�bet
		// ud fra den vinkel en linje skulle tegnes fra centrum og ud
		int retVal = 0;
		double afstand = this.getPlanetensStartAfstandKredsl�bet()
				+ this.beregnAfstandTilbagelagtIalt(d) % this.planetSE.getOmkreds();
		retVal = (int) ((afstand / this.planetSE.getOmkreds()) * 360);

		this.planetensGradIKredsl�bet = retVal;
	}

	public void LogPlanetXY() {
		System.out.println(this.getPlanetName() + "(X,Y) : " + this.planetSE.x + "," + this.planetSE.y + " Vinkel : "
				+ planetensGradIKredsl�bet);
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
		g.fillArc((int) (int) x, (int) y, (int) r, (int) r, (int) this.getPlanetensGradIKredsl�bet() * -1,
				(int) this.getDistancePrClick());
		// g.drawArc(x, y, r, r, (int) this.planetensPostionIKredsl�bet(ec) *
		// -1, (int) this.planet.getDistancePrClick());
		this.getPlanetX(this.getPlanetensGradIKredsl�bet());
		this.getPlanetY(this.getPlanetensGradIKredsl�bet());
		g.fillOval((int) this.getPlanetX(this.getPlanetensGradIKredsl�bet()),
				(int) this.getPlanetY(this.getPlanetensGradIKredsl�bet()), 10, 10);
		LogPlanetXY();
	}

}
