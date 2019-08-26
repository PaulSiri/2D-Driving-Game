package game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//This class has the graphics for the instrction screen
public class Instruction {

	public Rectangle backButton = new Rectangle(GameFrame.WIDTH / 2 - 150, 700, 300, 125);
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Font f0 = new Font ("arial", Font.BOLD, 50);
		Font f1 = new Font ("arial", Font.PLAIN, 30);
		g.setFont(f0);
		g.setColor(Color.WHITE);
		g.drawString("Instuctions", GameFrame.WIDTH / 2 - 150, 100);
		g.drawString("Back", GameFrame.WIDTH / 2 - 65, 780);
		g.setFont(f1);
		g.drawString("Use the arrow keys to move", GameFrame.WIDTH / 2 - 180, 350);
		g.drawString("And avoid the obstacles to get points!", GameFrame.WIDTH / 2 - 230, 450);
		
		g2D.draw(backButton);
	}
}
