package game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

//This class has the graphics for the menu screen
public class Menu {
	
	private XML xml = new XML(); 

	public Rectangle playButton = new Rectangle(GameFrame.WIDTH / 2 - 150, 150, 300, 125);
	public Rectangle instrucButton = new Rectangle(GameFrame.WIDTH / 2 - 150, 400, 300, 125);
	public Rectangle exitButton = new Rectangle(GameFrame.WIDTH / 2 - 150, 650, 300, 125);
	
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Font f0 = new Font ("arial", Font.BOLD, 50);
		Font f1 = new Font ("arial", Font.BOLD, 20);
		g.setFont(f0);
		g.setColor(Color.WHITE);
		g.drawString("ROAD RAGE", GameFrame.WIDTH / 2 - 150, 100);
		g.drawString("Play", GameFrame.WIDTH / 2 - 50, 220);
		g.drawString("Instuctions", GameFrame.WIDTH / 2 - 130, 470);
		g.drawString("Exit", GameFrame.WIDTH / 2 - 50, 720);
		g.setFont(f1);
		g.drawString("High Score", GameFrame.WIDTH / 2 + 250, 100);
		try {
			g.drawString(xml.readxml(), GameFrame.WIDTH / 2 + 250, 120);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2D.draw(playButton);
		g2D.draw(instrucButton);
		g2D.draw(exitButton);
	}
}
