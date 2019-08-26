package game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//This class controls mouse inputs in the program
public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	//When the mouse is pressed
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(); //Get the x position
		int my = e.getY(); //Get the y position

		if (GameFrame.state == GameFrame.state.MENU) { //If the menu screen is active
			//These are the button ranges for the menu screen, if a press is within any button
			//then activate the appropriate action
			if (mx >= (GameFrame.WIDTH / 2 - 150) && mx <= (GameFrame.WIDTH / 2 + 150)) {
				if (my >= 150 && my <= 275) {
					GameFrame.state = GameFrame.STATE.GAME; //Play button
				}
				if (my >= 650 && my <= 775) {
					System.exit(0); //Exit button
				}
				if (my >= 400 && my <= 525) {
					GameFrame.state = GameFrame.STATE.INSTRUCTION; //Instruction button
				}
			}
		} else if (GameFrame.state == GameFrame.state.GAMEOVER) { //If the game over screen is active
			//These are the button ranges for the game over screen, if a press is within any button
			//then activate the appropriate action
			if (mx >= (GameFrame.WIDTH / 2 - 150) && mx <= (GameFrame.WIDTH / 2 + 150)) {
				if (my >= 400 && my <= 525) {
					GameFrame.score = 0; //Reset score
					GameFrame.state = GameFrame.STATE.GAME; //Play again button

				}
				if (my >= 650 && my <= 775) {
					System.exit(0); //Exit button
				}
			}
		} else if (GameFrame.state == GameFrame.state.INSTRUCTION) { //If the instruction screen is active
			//These are the button ranges for the game over screen, if a press is within any button
			//then activate the appropriate action
			if (mx >= (GameFrame.WIDTH / 2 - 150) && mx <= (GameFrame.WIDTH / 2 + 150)) {
				if(my >= 700 && my <= 825) {
					GameFrame.state = GameFrame.STATE.MENU; //Back button
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
