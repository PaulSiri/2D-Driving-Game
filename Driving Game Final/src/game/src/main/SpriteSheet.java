package game.src.main;

import java.awt.image.BufferedImage;

//The SpriteSheet class is used to hold the game's spritesheet and to grab individual sprites from it
public class SpriteSheet {
	
	private BufferedImage image; //An image variable to hold the spritesheet file
	
	//Constructor
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	/*This method returns a buffered image of an individual sprite which is taken from the spritesheet
	*It intakes the column and row of the spritesheet to be accessed, as well as the width and height
	*of the desired sprite */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		//Create a subimage from the image of the spritesheet
		BufferedImage img = image.getSubimage((col * 66) - 66, (row * 66) - 66, width, height);
		return img; //Return the subimage
		
	}
}
