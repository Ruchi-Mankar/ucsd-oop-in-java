package module1;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
/** HelloWorld
  * An application with two maps side-by-side zoomed in on different locations.
  * Author: UC San Diego Coursera Intermediate Programming team
  * @author Ruchi Mankar
  * Date: June 28, 2023
  * */
//Your goal: add code to display second map, zoom in, and customize the background.
public class HelloWorld extends PApplet
{
	// You can ignore this.  It's to keep eclipse from reporting a warning
	private static final long serialVersionUID = 1L;
	//This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	private static final boolean offline = false;

	UnfoldingMap map1; // given map: La Jolla, CA
	UnfoldingMap map2; // our map => VJTI,mumbai

	public void setup() {
		size(850, 600, P2D);  // Set up the Applet window to be 800x600 (w x h)
		                      // The OPENGL argument indicates to use the Processing library's 2D drawing

		background(0, 0, 0, 0);
		AbstractMapProvider provider = new Google.GoogleTerrainProvider(); // Select a map provider
		int zoomLevel = 10; // Set a zoom level
		
		if (offline) {
			// If you are working offline, you need to use this provider 
			// to work with the maps that are local on your computer.  
			provider = new MBTilesMapProvider(mbTilesString);
			// 3 is the maximum zoom level for working offline
			zoomLevel = 3;
		}
		// The 2nd-5th arguments give the map's x, y, width and height
		// The 6th argument specifies the map provider.  
		// There are several providers built-in.
		// Note if you are working offline you must use the MBTilesMapProvider
		map1 = new UnfoldingMap(this, 50, 50, 350, 500, provider);

		// The next line zooms in and centers the map at 32.9 (latitude) and -117.2 (longitude)
	    map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
		
		// This line makes the map interactive
		MapUtils.createDefaultEventDispatcher(this, map1);

		map2 = new UnfoldingMap(this, 450, 50, 350, 500, provider);
		map2.zoomAndPanTo(zoomLevel, new Location(19.0f,72.85f));
		MapUtils.createDefaultEventDispatcher(this, map2);
	}

	//Draw the Applet window.
	public void draw() {
		map1.draw();
		map2.draw();
	}

	
}