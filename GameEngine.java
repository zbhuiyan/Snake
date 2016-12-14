/**
* GameEngine.java
* The game engine is responsible for updating the game at specific
* intervals, and in response to any event. It accepts user input
* from the keyboard and handles the game's logic, game loop, and 
* window creation.
*/


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.*; //For Java JFrame class
import java.util.*;
import java.lang.InterruptedException;
// import java.time.Clock;

public class GameEngine implements KeyListener{

// Instance variables
	private static final long TIME_BETWEEN_FRAMES = 1000L / 50L; //Number of ms to pass between each frame.
	private static final int MAX_DIRECTIONS = 2; //The number of maximum directions we can queue up.
	private SnakePanel snakePanel;
	private Random random;
	private Clock clock;
	private boolean isNewGame;
	private boolean isGameOver;
	private boolean isPaused;
	private String direction;
	private LinkedList<String> directions; //Linked list of Direction objects
	private int numFoodEaten;
	private int score;
	private SnakeBody snake;
	private Location foodLocation;
	private int foodValue;


	/**
	* Here we create a constructor to make a new GameEngine. 
	* It utilizes the KeyAdapter abstract class which allows the
	* program to read the user's keyboard inputs.
	* 
	*/
	public GameEngine(){
		this.snakePanel = new SnakePanel(); //Initialize game panels.

		// KeyAdapter adapter = new KeyAdapter();
		// addKeyListener(new KeyAdapter()){
			
		// }	
	}
	public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {}


 
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode(); //getKeyCode returns the integer KeyCode associated with the key
		int directionSize = directions.size(); //current size of LinkedList
	
		switch(keyCode){ 
			case KeyEvent.VK_UP:
				if((!isPaused && !isGameOver) && (directionSize < MAX_DIRECTIONS)){
					directions.addLast("U");
				}
				break;
			case KeyEvent.VK_DOWN:
				if((!isPaused && !isGameOver) && (directionSize < MAX_DIRECTIONS)){
					directions.addLast("D");
				}
				break;
			case KeyEvent.VK_LEFT:
				if((!isPaused && !isGameOver) && (directionSize < MAX_DIRECTIONS)){
					directions.addLast("L");
				}
				break;
			case KeyEvent.VK_RIGHT:
				if((!isPaused && !isGameOver) && (directionSize < MAX_DIRECTIONS)){
					directions.addLast("R");
				}
				break;
			case KeyEvent.VK_SPACE: 
				if(!isGameOver){
					this.isPaused = !isPaused; //So the user can pause and unpause the game
					clock.pause(isPaused); // Sets the isPaused to be what it is currently after negating							
				}						
		}
	}



	/**
	* This method sets up the game and the game loop which will run
	* until the window is closed.
	*  
	*/
	public void beginGame(){
		this.snake = new SnakeBody();
		this.directions = new LinkedList<String>();
		clock = new Clock(3.0f);
		this.isNewGame = true;

		while(true){
			long startTime = System.nanoTime(); //Precise start time for current frame
			// clock.update();
			snakePanel.moveSnake(snake.getBody()); // This repaints the board
			long time = (System.nanoTime() - startTime)/1000000L;

			if (clock.hasPassedCycle()){
				this.updateGame();
			}
			try{
				if (time < TIME_BETWEEN_FRAMES){
					Thread.sleep(TIME_BETWEEN_FRAMES - time);
				}
			}
			catch(InterruptedException ex){
				System.out.println("Error: " + ex);
			}
		}
	}


	public Location spawnFood(){
		this.random = new Random();
		this.foodLocation = new Location(random.nextInt(50), random.nextInt(50)); //Get random location for number of free spaces on board
		return this.foodLocation;
	}	


	public void updateGame(){
		if (snake.isDead()){
			isGameOver = true;
			clock.pause(true);
		}
		else if (snake.getBody().get(0).equals(foodLocation)) {
		  Location tail = snake.getBody().getLast();
		  snake.move(direction);
		  snake.addSegment(tail);
		  this.score += foodValue;
		  this.spawnFood();
		}
	}


	/**
	* This method gets the player's current score.
	*
	* @return int score  
	*/
	public int getScore(){
		return this.score;
	}

	/**
	* This method gets the number of food eaten thus far.
	*
	* @return int numFoodEaten 
	*/
	public int getNumFoodEaten(){
		return this.numFoodEaten;

	}

	/**
	* This method gets the current direction.
	*
	* @return direction  
	*/
	public String getCurrentDirection(){
		return this.directions.peek(); 
	}


	/**
	* This method returns whether the game is paused or not.
	*
	* @return boolean isPaused  
	*/
	public boolean isPaused(){
		return this.isPaused;
	}

	/**
	* This method returns whether this is a new game or not.
	*
	* @return boolean isNewGame
	*/
	public boolean isNewGame(){
		return this.isNewGame;
	}

	/**
	* This method returns if the game is over.
	*
	* @return boolean isGameOver  
	*/
	public boolean isGameOver(){
		return this.isGameOver;
	}



	public static void main(String[] args){
		new SnakePanel();
	}

}