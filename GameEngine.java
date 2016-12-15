/**
 * GameEngine.java
 * The game engine is responsible for updating the game at specific
 * intervals, and in response to any event. It accepts user input
 * from the keyboard and handles the game's logic, game loop, and 
 * window creation. 
 * Primarily worked on by: Zarin Bhuiyan, Emily Yeh, and Anne Ku
 */

import java.util.*;
import java.lang.InterruptedException;

public class GameEngine {
  
// Instance variables
  private static final long TIME_BETWEEN_FRAMES = 10000L / 50L; //Number of ms to pass between each frame.
  private SnakePanel snakePanel;
  private Random random;
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
  } 
  
  /**
   * This method sets up the game and the game loop which will run
   * until the window is closed.
   *  
   */  
  public void beginGame(){
    this.isNewGame = true;
    
    while (!isGameOver) {
      tail = snake.getBody().getLast();
      long startTime = System.nanoTime(); //Precise start time for current frame
      snakePanel.moveSnake(snake.getBody(), tail);
      if (isNewGame) {
        foodValue = snakePanel.placeFood(spawnFood());
        isNewGame = false;
      }
      long time = (System.nanoTime() - startTime)/1000000L;
      updateGame();
      
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

  /**
   * This method tries to pause the game.
   *  
   */ 
  public void pause () {
    try {
      Thread.sleep(1);
    } catch (InterruptedException ex) {
      System.out.println("Error: " + ex);
    }
  }
  

  /**
   * This method tries to pause the game.
   *  
   * @return Location
   */ 
  public Location spawnFood() {
    random = new Random();
    foodLocation = new Location(random.nextInt(50), random.nextInt(50)); //Get random location for number of free spaces on board
    return foodLocation;
  } 
  
  /**
   * This method updates the game. It checks whether the game is paused, if the
   * snake is dead, and if the snake has eaten a pixel of food.
   *  
   */ 
  public void updateGame(){
    
    while (snakePanel.getPaused()) {
      pause();
    }

    if (snake.isDead()){
      isGameOver = true;
    }

    else if (snake.getBody().getFirst().equals(foodLocation)) { // When snake eats something
      Location tail = snake.getBody().getLast();
      
      try {
        direction = snakePanel.getCurrentDirection();
        snake.move(direction);
      } catch (NoSuchElementException e) {
          snake.move(direction);
      }
      snake.addSegment(tail);
      this.score += foodValue;
      snakePanel.updateFood(foodValue);
      snakePanel.updateScore(score);
      foodValue = snakePanel.placeFood(spawnFood());
    }
    
    else {
      try {
        direction = snakePanel.getCurrentDirection();
        snakePanel.moveSnake(snake.getBody(), tail);
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