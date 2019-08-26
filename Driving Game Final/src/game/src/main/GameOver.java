package game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//This class has the graphics for the game over screen
public class GameOver {
	public Rectangle playButton = new Rectangle(GameFrame.WIDTH / 2 - 150, 400, 300, 125);
	public Rectangle exitButton = new Rectangle(GameFrame.WIDTH / 2 - 150, 650, 300, 125);
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Font f0 = new Font ("arial", Font.BOLD, 50);
		g.setFont(f0);
		g.setColor(Color.WHITE);
		g.drawString("Final Score: " + GameFrame.score, GameFrame.WIDTH / 2 - 180, 150);
		g.drawString("Play Again", GameFrame.WIDTH / 2 - 125, 470);
		g.drawString("Exit", GameFrame.WIDTH / 2 - 50, 720);
		
		g2D.draw(playButton);
		g2D.draw(exitButton);
	}
}
