package game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//The KeyInput class is used to handle keyboard inputs from the player
public class KeyInput extends KeyAdapter {

	GameFrame game; //The GameFrame
	
	//Constructor
	public KeyInput(GameFrame game) {
		this.game = game;
	}
	
	//Method to handle key presses
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e); //GameFrame keyPressed method
	}
	
	//Method to handle key releases
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e); //GameFrame keyReleased method
	}
}
