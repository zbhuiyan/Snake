/**
 * GameEngine.java
 * The game engine is responsible for updating the game at specific
 * intervals, and in response to any event. It accepts user input
 * from the keyboard and handles the game's logic, game loop, and 
 * window creation.
 */

import java.util.*;
import java.lang.InterruptedException;

public class GameEngine {
  
// Instance variables
  private static final long TIME_BETWEEN_FRAMES = 10000L / 50L; //Number of ms to pass between each frame.
  private SnakePanel snakePanel;
  private Random random;
  private Clock clock;
  private boolean isNewGame;
  private boolean isGameOver;
  private boolean isPaused;
  private String direction;
  private int numFoodEaten;
  private int score;
  private SnakeBody snake;
  private Location foodLocation;
  private Location tail;
  private int foodValue;
  private int minSnakeSize;
  
  
  /**
   * Here we create a constructor to make a new GameEngine. 
   * It utilizes the KeyAdapter abstract class which allows the
   * program to read the user's keyboard inputs.
   * 
   */
  public GameEngine(){
    this.snakePanel = new SnakePanel(); //Initialize game panels.
    this.isNewGame = false;
    this.isGameOver = false;
    this.isPaused = false;
    this.numFoodEaten = 0;
    this.score = 0;    
    this.snake = new SnakeBody();
    this.direction = "D"; // Default direction
    this.minSnakeSize = 2;
  } 
  
  /**
   * This method sets up the game and the game loop which will run
   * until the window is closed.
   *  
   */
  public void beginGame(){
    clock = new Clock(3.0f);
    this.isNewGame = true;
    
    while (!isGameOver) {
      long startTime = System.nanoTime(); //Precise start time for current frame
      clock.updateClock();
      
      tail = snake.getBody().getLast();
      
      // System.out.println("tail" + tail);
      // snakePanel.moveSnake(snake.getBody()); // This repaints the board
      
      long time = (System.nanoTime() - startTime)/1000000L;
      
      if (clock.hasPassedCycle()){
        updateGame();
      }
      
      try {
        if (time < TIME_BETWEEN_FRAMES){
          
          Thread.sleep(TIME_BETWEEN_FRAMES - time);
        }
      }
      catch(InterruptedException ex){
        System.out.println("Error: " + ex);
      }
    }
  }
  
  public Location spawnFood() {
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
      direction = snakePanel.getCurrentDirection();
      snake.move(direction);
      snake.addSegment(tail);
      this.score += foodValue;
      this.spawnFood();
    }
    
    else {
      try {
        direction = snakePanel.getCurrentDirection();
        snakePanel.moveSnake(snake.getBody(), tail);
        // snakePanel.eraseTail(tail);
        snake.move(direction);
      } catch (NoSuchElementException e) {
        snakePanel.moveSnake(snake.getBody(), tail);
        snake.move(direction);
        System.out.println(snake.getBody());
      }
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
    GameEngine test = new GameEngine();
    test.beginGame();

  }
}