package game.src.main;

import java.util.ArrayList;

//The Physics class handles collisions in the game
public class Physics {

	//This method checks for collisions between a Hurtbox (Player) and every active Hitbox
	public static boolean collision(Hurtbox hurt, ArrayList<Hitbox> hit) {
		for(int i = 0; i < hit.size(); i++) { //Check every Hitbox
			if(hurt.getBounds().intersects(hit.get(i).getBounds())) { //If there is a collision
				return true;
			}
		}//If there is no collision
		return false;
	}
}
