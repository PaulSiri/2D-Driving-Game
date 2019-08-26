package game.src.main;

import java.awt.image.BufferedImage;
import java.util.Random;

//The Sprites class is used to access individual sprites from the SpriteSheet
public class Sprites {
	private SpriteSheet ss; //The SpriteSheet
	
	private Random r = new Random(); //RNG to choose random obstacle sprites
	
	//Images for all the Sprites
	public BufferedImage playerUp, playerDown, rock, cone, box, log, sign, current;
	
	//Constructor
	public Sprites(GameFrame game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getSprites(); //Get all the individual Sprites
	}
	
	//This method gets all the individual sprites from the SpiteSheet
	private void getSprites() {
		playerUp = ss.grabImage(1, 4, 66, 66);
		playerDown = ss.grabImage(1, 1, 66, 66);
		rock = ss.grabImage(3, 2, 66, 66);
		cone = ss.grabImage(3, 3, 66, 66);
		box = ss.grabImage(4, 2, 66, 66);
		log = ss.grabImage(3, 1, 132, 66);
		sign = ss.grabImage(4, 3, 66, 132);
	}
	
	//This method randomly chooses an obstacle sprite
	//Used when generating new obstacles
	public void chooseObject() {
		switch(r.nextInt(5)) { //RNG from 0-4, then choose a sprite accordingly
		case 0:
			current = rock;
			break;
		case 1:
			current = cone;
			break;
		case 2: 
			current = box;
			break;
		case 3:
			current = log;
			break;
		case 4:
			current = sign;
		}
	}
}
