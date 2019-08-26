package game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

//The GameObject class is used for the obstacles that move down the screen in the game.
public class GameObject extends Box implements Hitbox {

	Random r = new Random(); //RNG to set the speed of obstacles

	private int speed = r.nextInt(5) + 3; //Speed of an obstacle

	private BufferedImage object; //Sprite for the obstacle

	private Sprites s; //Sprite resources

	private GameFrame game; //The GameFrame

	private ObjectController c; //Controller to interact with GameObjects

	//Constructor
	public GameObject(double x, double y, Sprites s, BufferedImage bi, GameFrame game, ObjectController c) {
		super(x, y);
		this.s = s;
		this.game = game;
		this.c = c;

		object = bi;
	}

	//Tick method to run every frame
	public void tick() {
		y += speed; //Move GameObject down screen

		if (y > game.HEIGHT) { //If the GameObject is off screen
			c.removeHitbox(this); //Remove it
			game.setObjectAvoided(game.getObjectAvoided() + 1); //Update objects avoided
			game.setScore(game.getScore() + 5); //Update score
		}
	}

	//Render for GameObjects
	public void render(Graphics g) {
		g.drawImage(object, (int) x, (int) y, null); //Draw the sprite at correct position
	}

	//SETTERS AND GETTERS
	//////////////////////////////////////
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	///////////////////////////////////////////

	//Rectangle to act as a hitbox for the GameObject
	public Rectangle getBounds() {
		if (object == s.box || object == s.cone || object == s.rock) {
			return new Rectangle((int) x + 10, (int) y + 10, 45, 45);
		} else if (object == s.log) {
			return new Rectangle((int) x + 12, (int) y + 17, 110, 45);
		} else {
			return new Rectangle((int) x + 10, (int) y + 13, 45, 110);
		}
	}
}
