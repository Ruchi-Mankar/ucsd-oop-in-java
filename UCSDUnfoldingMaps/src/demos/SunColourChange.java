package demos;
import processing.core.PApplet;
import processing.core.PImage;

public class SunColourChange extends PApplet{
	PImage img;
	
	public void setup() {
		size(400,400);   //set canvas size
		background(255); //set canvas color
		stroke(0);		//set pen color
		img = loadImage("sun.jpg", "jpg");
	}
	public void draw() {
		img.resize(width, height);	//resize loaded image to full height and width of canvas
		image(img, 0, 0);			//display image 
		int[] color = sunColorSec(second());	//calculate color code for sun
		fill(color[0],color[1],color[2]);	//set sun color
		ellipse(width/2,height/2,width/6,height/6);	//draw sun
	}
	
	/** Return the RGB color of the sun at this number of seconds in the minute */
	public int[] sunColorSec(float seconds)
	{
		int[] rgb = new int[3];
		// Scale the brightness of the red based on the seconds.
		float diffFrom30 = Math.abs(30-seconds);
		//sun goes from black to red to orange
		float ratio = diffFrom30/30; //always between 0 and 1
		rgb[0] = (int)(255*ratio); //r
		rgb[1] = (int)(255*(ratio - 0.5)); //g
		rgb[2] = 0;  //b
		return rgb;
	}	
	public static void main (String[] args) {
		//Add main method for running as application
		PApplet.main(new String[] {"--present", "SunColourChange"});
	}
}


