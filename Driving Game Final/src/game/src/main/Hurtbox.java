package game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//The Hurtbox interface is used for the Box around the Player
//Hurtboxes collide with Hitboxes
public interface Hurtbox {

	//Methods that classes which implement this interface must have
	public void tick();
	
	public void render(Graphics g );
	
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}
