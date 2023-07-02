package demos;
import processing.core.PApplet;

public class Snowman extends PApplet {

	public void setup()
	{
		size(600, 600); //wxh
		background(0,100,110);//rgb
	}
	
	public void draw()
	{
		fill(255, 255, 255);
		ellipse(width/2, height/6 + 50, 150, 150); //face
		ellipse(width/2, (height/6 + 250), 300,300); //body
		fill(0,0,0);
		ellipse(width/2 - 20, height/6 + 40, 20, 20); //left eye
		ellipse(width/2 + 20, height/6 + 40, 20, 20); //right eye
		ellipse(width/2,height/2 - 50,30,30); //button
		ellipse(width/2,height/2,30,30);      //button
		ellipse(width/2,height/2 + 50,30,30); //button
		rect(width/2 - 85,height/6 ,170,10);  // hat base
		rect(width/2 - 75,height/6 - 60 ,150,60); // hat
		fill(240,60,24);
		triangle(width/2,height/6 +60, width/2,height/6 + 75, width/2 - 60, height/6 + 70); //nose
		noFill();
		arc(width/2, height/6 + 60, 50, 50, 0, PI); //smile
	}
}
