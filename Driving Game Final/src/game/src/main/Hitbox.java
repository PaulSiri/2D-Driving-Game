package game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//The Hitbox interface is used for the Box around the GameObjects
//Hitboxes collide with Hurtboxes
public interface Hitbox {
	
	//Methods that classes which implement this interface must have
	public void tick();
	
	public void render(Graphics g );
	
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	
}
