/**
 * SnakeBody class: given the current state of the Snake,
 * calculate the next state of the game.
 */

import java.util.*; 

public class SnakeBody {
  
  // Defining the game's grid and initializing the snake's body as a linkedlist of location objects:
  private final int GAME_SIZE = 50;
  private LinkedList<Location> body; 
  
  // Constructor -- defining the snake's body
  public SnakeBody() {
    body = new LinkedList<Location>();
    body.add(new Location(0, (GAME_SIZE/2)));
  }
  
  public LinkedList<Location> getBody () {
    return body;
  }
  
  /**
  * Updates the body according to a string 'direction' that determines where the head goes
  */
  public void move(String direction) {
    //Update all segments except head
    for (int i = body.size()-1; i >= 1; i--) { 
      // setting each non-head component to the component after it.
      body.set(i, body.get(i-1));
    }
    //obtaining the head of the snake:
    Location head = body.get(0);
    Location new_head = head;
    //places the head according to the string inputted into this method:
    if (direction.equals("R")) {
      new_head = new Location(head.getX() + 1, head.getY());
    } else if (direction.equals("L")) {
      new_head = new Location(head.getX() - 1, head.getY());
    } else if (direction.equals("U")) { //remember that up *subtracts* from the y-coordinate!
      new_head = new Location(head.getX(), head.getY() - 1);
    } else if (direction.equals("D")) {
      new_head = new Location(head.getX(), head.getY() + 1);
    }
    body.set(0, new_head);
  }
  
  /**
  * Returns a boolean that represents whether the snake is alive by checking for two conditions:
  * 1. Is the head is out of bounds? 
  * 2. Is the head intersecting a part of its body? 
  */
  public boolean isDead() {
    Location head = body.get(0); // retrieve the head of the snake
    if (head.getX() < 0 || head.getX() > 50 || head.getY() < 0 || head.getY() > 50) {
      return true;
    } else if (hasDuplicates()) {
      return true;
    }
    return false;
  }
  
  /** 
   * Helper method for checking if the head intersects a part of its body?
   */
  private boolean hasDuplicates() {
    return body.lastIndexOf(body.getFirst()) != 0;
  }

  /**
  * Adds another Location object to the linkedlist
  */
  public void addSegment (Location tail) {
    body.add(tail);
  }
  
  /**
   * Get the string representation of the Snake Body
   */
  public String toString() {
    return body.toString();
  }
  
  public static void main(String[] args) {
    SnakeBody snake = new SnakeBody();
    System.out.println(snake.getBody());
    Location tail = snake.getBody().getLast();
    snake.move("R");
    snake.addSegment(tail);
    System.out.println(snake.getBody());
  }
}