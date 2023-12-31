package module5;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

// Change SimplePointMarker to CommonMarker as the very first thing you do
// in module 5 (i.e. CityMarker extends CommonMarker). It will cause an error.
// That's what's expected.
public class CityMarker extends CommonMarker {

	public static int TRI_SIZE = 5; // The size of the triangle marker

	public CityMarker(Location location) {
		super(location);
	}

	public CityMarker(Feature city) {
		super(((PointFeature) city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}

	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y) {
		// Implement this method
		String text = "Name: " + getCity() + ".\nCountry: " + getCountry() + ".\nPopulation: " + getPopulation();
		x = x - TRI_SIZE;
		pg.fill(245);
		pg.rect(x - 5, y + TRI_SIZE, pg.textWidth(text) + 10, 45);
		pg.fill(0);
		pg.text(text, x,
				y + TRI_SIZE * 2 + 10);
	}

	/*
	 * Local getters for some city properties.
	 */
	public String getCity() {
		return getStringProperty("name");
	}

	public String getCountry() {
		return getStringProperty("country");
	}

	public float getPopulation() {
		return Float.parseFloat(getStringProperty("population"));
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		// Save previous drawing style
		pg.pushStyle();

		// IMPLEMENT: drawing triangle for each city
		pg.fill(150, 30, 30);
		pg.triangle(x, y - TRI_SIZE, x - TRI_SIZE, y + TRI_SIZE, x + TRI_SIZE, y + TRI_SIZE);

		// Restore previous drawing style
		pg.popStyle();
	}
}