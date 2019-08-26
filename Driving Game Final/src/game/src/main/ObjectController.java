package game.src.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

//The ObjectController class is used to control all the obstacles in the game
public class ObjectController {

	private ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>(); //Hitbox arraylist for every obstacle

	private GameFrame game; //The GameFrame

	private Hitbox hit; //A hitbox

	private Random r = new Random(); //RNG to choose where obstacle is generated

	private Sprites s; //Sprite resources

	//Constructor
	public ObjectController(GameFrame game, Sprites s) {
		this.game = game;
		this.s = s;
	}

	//This method creates new obstacles to make up for ones that have already been avoided and removed
	public void CreateObject(int objectCount) {
			s.chooseObject(); //Randomly choose a type of obstacle to generate
			addHitbox(new GameObject(r.nextInt(game.WIDTH - 66), 0, s, s.current, game, this)); //Create the obstacle
	}

	//Tick method to run every frame
	public void tick() {
		for (int i = 0; i < hitboxes.size(); i++) { //Run through every hitbox
			hit = hitboxes.get(i); //Get a hitbox from the arraylist

			hit.tick(); //Run its tick method
		}

		if (game.getObjectCount() > game.hitboxes.size()) { //If there are less hitboxes than there should be
			CreateObject(game.getObjectCount()); //Make up for it
		}

	}

	//Render method to run through all hitbox renders
	public void render(Graphics g) {
		for (int i = 0; i < hitboxes.size(); i++) { //Run through all hitboxes
			hit = hitboxes.get(i); //Get a hitbox
			hit.render(g); //Render the hitbox
		}
	}

	//Method to add a hitbox to the arraylist
	public void addHitbox(Hitbox box) {
		hitboxes.add(box);
	}

	//Method to remove a hitbox from the arraylist
	public void removeHitbox(Hitbox box) {
		hitboxes.remove(box);
	}

	//Getter for the hitboxes arraylist
	public ArrayList<Hitbox> getHitboxes() {
		return hitboxes;
	}

}
