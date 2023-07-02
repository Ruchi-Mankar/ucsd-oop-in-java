package module3;
import java.util.ArrayList;
import java.util.*;
//Processing library
import processing.core.PApplet;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
//Parsing library
import parsing.ParseFeed;

public class EarthquakeCityMap extends PApplet {
	private static final long serialVersionUID = 1L;
	private static final boolean offline = false;
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	private UnfoldingMap map;
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    //map.zoomToLevel(1);
	    MapUtils.createDefaultEventDispatcher(this, map);	//setup method
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();
	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    //calls createMarker (see below) to create a new SimplePointMarker for each PointFeature in earthquakes.  
	    //Then add each new SimplePointMarker to the List markers (so that it will be added to the map in the line below
	    for (PointFeature quake : earthquakes) {
			Marker mk = createMarker (quake);
			markers.add (mk);
		}
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(markers);
	}
		
	/* createMarker: A suggested helper method that takes in an earthquake 
	 * feature and returns a SimplePointMarker for that earthquake*/
	private SimplePointMarker createMarker(PointFeature feature)
	{  
		System.out.println(feature.getProperties());
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		
		//Processing's color method to generate int of a color
	    int yellow = color(255, 255, 0);
	    int orange = color (242, 151, 63);
		int red = color (255, 0, 0);
		if (mag < THRESHOLD_LIGHT) {
			marker.setColor (yellow);
			marker.setRadius (7);
		} else {

			marker.setColor (red);
			marker.setRadius (15);
		}
		if (mag < THRESHOLD_MODERATE && mag > THRESHOLD_LIGHT) {
			marker.setColor (orange);
			marker.setRadius (11);
		}
	    // Finally return the marker
	    return marker;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}

	// helper method to draw key in GUI
	private void addKey() 
	{	
		//fill (40, 40, 40);
		//rect(20, 50, 190, 270, 5, 17, 17, 5);
		fill (255, 255, 255);
		textAlign (LEFT, CENTER);
		textSize (20);
		text ("Earthquake key", 35, 80);
		textSize (15);
		text ("5.0+ Magnitude", 70, 120);
		text ("4.0+ Magnitude", 70, 170);
		text ("Below 4.0+ ", 70, 220);
		fill (255, 0, 0); //red
		ellipse (40, 120, 15, 15);
		fill (242, 151, 63); //yellow
		ellipse (40, 170, 11, 11);
		fill (255, 255, 0); //green
		ellipse (40, 220, 7, 7);
	}
}


