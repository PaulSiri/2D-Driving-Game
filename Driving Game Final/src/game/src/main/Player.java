package game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

//The Player class is used for the car that the user drives
public class Player extends Box implements Hurtbox{ //The Player has a Hurtbox 

	//X and Y velocity 
	private double velX = 0;
	private double velY = 0;
	
	private String name = System.getProperty("user.name");
	
	private XML xml = new XML(); //for XML writing
	
	private GameFrame game; //The GameFrame
	
	private Sprites s; //Sprite resources
	
	private BufferedImage player; //The player sprite
	
	//Constructor
	public Player(double x, double y, Sprites s, GameFrame game) {
		super(x,y);
		this.s = s;
		this.game = game;
		
		this.player = s.playerUp; //Set initial sprite as facing upwards
	}
	
	//Tick method for Player to be called every frame
	public void tick() {
		//Move player
		x+= velX;
		y+= velY;
		
		//Restriction on the bounds of where the Player can move
		if(x<=0) {
			x=0;
		}
		if(x>840) {
			x=840;
		}
		if(y<=40) {
			y=40;
		}
		if(y>825) {
			y=825;
		}
		
		//If the Player hits a Hitbox
		if(Physics.collision(this, game.hitboxes)) {
			GameFrame.state = GameFrame.state.GAMEOVER; //Game over
			if(game.score > xml.getHighScore())
			xml.createhighscores(game.score, name);
			//Reset position
			x = 410;
			y = 825;
			game.hitboxes.clear(); //Remove all hitboxes
		}
	}
	
	//Render method for Player
	public void render(Graphics g) {
		g.drawImage(player, (int)x,(int)y,null); //Draw the Player at its location
	}
	
	
	//SETTERS AND GETTERS////////////////////////////////////////////
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getVelX(){
		return velX;
	}
	
	public double getVelY(){
		return velY;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	///////////////////////////////////////////////////////////////////
	
	//Method to change the sprite of the player
	//Intakes a sprite and sets the Player to that sprite
	public void changeSprite(BufferedImage bi) {
		player = bi;
	}
	
	//Rectangle representing the Hurtbox of the player
	public Rectangle getBounds() {
		return new Rectangle((int)x + 17, (int)y + 5, 40, 55);
	}
}
