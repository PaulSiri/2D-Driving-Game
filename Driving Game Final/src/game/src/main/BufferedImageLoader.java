package game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//The BufferedImageLoader class is used to load in image files from the res folder 
public class BufferedImageLoader {
	private BufferedImage image; //An image variable for a file to be loaded onto
	
	//This method loads the file for an image
	public BufferedImage loadImage(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path)); //Get image from res folder
		return image; //Return the image
	}
}
