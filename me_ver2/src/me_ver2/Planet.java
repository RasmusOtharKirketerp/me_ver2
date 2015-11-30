package me_ver2;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Planet {
    private String planetName;
    private int afstandTilCenter = 0;
    private SolarElement SE;
	ArrayList<Planet> planeterIkredsloeb       = new ArrayList<Planet>();

	
// CONSTRUCTOR ------------------------------------------
	public Planet(String planetName, SolarElement SE) {
		setPlanetName(planetName);
		this.SE = SE;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	
	public void addPlanet(Planet planet, int in_afstandTilCenterPlanet){
		this.afstandTilCenter = in_afstandTilCenterPlanet;
		planeterIkredsloeb.add(planet);
	}
	
   public void drawPlanet(Graphics2D g, Screen s) {
	   g.setColor(this.SE.color);
	   g.fillOval(SE.x, SE.y, SE.omkreds, SE.omkreds);
	   g.drawString(planetName, 30, 30);
	   if (this.planeterIkredsloeb.size()>0)
		   drawChildPlanets(g, s);
   }
   
   public void drawChildPlanets(Graphics2D g, Screen s){
	   for (int childPlanetsCounter = 0; childPlanetsCounter < this.planeterIkredsloeb.size();childPlanetsCounter++){
		   // først tegn kredsløbet, rundt om denne planet
		   //planeterIkredsloeb.get(childPlanetsCounter).drawPlanet(g, s);
		   g.drawOval(this.SE.x, this.SE.y, planeterIkredsloeb.get(childPlanetsCounter).afstandTilCenter, planeterIkredsloeb.get(childPlanetsCounter).afstandTilCenter);
		   
	   }
   }

}
