package game.src.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * Comments by Paul Siri
 * 
 * This program is run off of a single JFrame, this one here, and uses enums
 * to switch between modes or states.
 * 
 * The game itself is a birds-eye-view driving game where the player must avoid
 * obstacles moving across the road to score points.
 * 
 * This program makes use of ArrayLists to track obstacles, Key and Mouse Listeners, 
 * BufferedImages and Graphics to render at 60fps, Rectangles for collisions, and many
 * custom made classes to run the game
 */



public class GameFrame extends JFrame implements Runnable {

	private JPanel contentPane;

	//Dimensions for frame
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;

	private boolean running = false; //To determine if game is running
	private Thread thread;

	//Image resources 
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	public static int objectCount = 12; //Number of objects that are on screen at any given time
	public int objectAvoided = 0; //Number of objects a player has avoided
	public static int score = 0; //Player's score

	//Objects from custom classes
	public Player p;
	private ObjectController c;
	private Sprites s;
	private Menu menu;
	private Instruction instruction;
	private GameOver gameOver;
	
	//Arraylist for obstacle hitboxes
	public ArrayList<Hitbox> hitboxes;

	//Enumeration to determine what screen game should be showing
	public static enum STATE {
		MENU, GAME, GAMEOVER, INSTRUCTION
	};

	//Set first screen to menu
	public static STATE state = STATE.MENU;

	//This method initializes anything that the program needs before startup
	public void init() {
		requestFocus(); // Auto selects window so key inputs work right away
		BufferedImageLoader loader = new BufferedImageLoader(); //Used to load images from resources

		//Attempt to load images
		try {
			spriteSheet = loader.loadImage("/Car Sprite Sheet.png");
			background = loader.loadImage("/Background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Listeners for player input
		addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());

		//Initializing objects
		s = new Sprites(this);
		menu = new Menu();
		instruction = new Instruction();
		gameOver = new GameOver();
		p = new Player(410, 825, s, this);
		c = new ObjectController(this, s);
		hitboxes = c.getHitboxes();
		c.CreateObject(objectCount);
	}

	//This is the game loop, it will continue to run while the game is playing
	public void run() {
		init(); //Initialize method
		
		//These are all for keeping frame rate (60fps)
		long lastTime = System.nanoTime(); 
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks; // for conversions from time to ticks and vice versa
		double timeDiff = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		//While the game is active
		while (running) {
			long now = System.nanoTime(); //Update time
			timeDiff += (now - lastTime) / ns;
			lastTime = now;

			//If enough time for a tick has past
			if (timeDiff >= 1) {
				tick(); //run tick method
				updates++;
				timeDiff--;
			}
			render(); //Render screen
			frames++;

			//Refresh every second
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
			}

		}
		stop(); //End game if not running
	}

	//This method is called every tick (60th of a second) to process the game
	private void tick() {
		if (state == STATE.GAME) { //If the game screen is being played
			p.tick(); //Player tick
			c.tick(); //ObjectController tick
		}
	}

	//This method renders the graphics for the game
	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); //Processes buffers to load screens for render

		if (bs == null) {
			createBufferStrategy(3); // Creates 3 buffers
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		/////////////////////// everything in here will draw graphics for the next loadout
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this); //Background

		if (state == STATE.GAME) { //Render for the game screen
			p.render(g); //Player render
			
			//Score display
			Font f0 = new Font ("arial", Font.BOLD, 50);
			g.setFont(f0);
			g.setColor(Color.WHITE);
			g.drawString("Score:" + score, GameFrame.WIDTH / 2 - 85, 100);

			c.render(g); //Obstacle render
		}else if(state == STATE.MENU) { //Render for menu screen
			menu.render(g); //Menu render
		}else if (state == STATE.GAMEOVER) { //Render for gameover screen
			gameOver.render(g); //Gameover render
		}else if (state == STATE.INSTRUCTION) { //Render for instruction screen
			instruction.render(g); //Instruction render
		}
		///////////////////////
		g.dispose(); //Get rid of last render
		bs.show(); //Show next render
	}

	//Method to start game
	private synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	//Method to end game
	private synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

	//Key inputs for when a key is released
	//Must have a key listener set up
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT && p.getVelX() > 0) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT && p.getVelX() < 0) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_UP && p.getVelY() < 0) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_DOWN && p.getVelY() > 0) {
			p.setVelY(0);
		}
	}

	//Key inputs for when a key is pressed
	//Must have a key listener set up
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (state == STATE.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
				p.changeSprite(s.playerUp);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
				p.changeSprite(s.playerDown);
			}
		}

	}

	//SETTERS AND GETTERS FOR VARIABLES
	//////////////////////////////////////////////////
	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}

	public int getObjectAvoided() {
		return objectAvoided;
	}

	public void setObjectAvoided(int objectAvoided) {
		this.objectAvoided = objectAvoided;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	///////////////////////////////////////////////////////////

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		GameFrame game = new GameFrame(); //Create game frame
		game.setVisible(true); //Make it visible

		game.start(); //Start the game 

	}

	/**
	 * Create the frame.
	 */
	
	//Parameters for the game frame
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}


}
