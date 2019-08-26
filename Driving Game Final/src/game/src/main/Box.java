package game.src.main;

import java.awt.Rectangle;

//The Box class is used as a superclass for classes which need either Hitboxes or Hurtboxes
public class Box {
	
	public double x,y; //X and Y coordinates of the Box
	
	//Constructor
	public Box (double x, double y) {
		this.x =x;
		this.y = y;
	}
	
	//Rectangle representing the Box
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int)x, (int)y, width, height);
	}
}
