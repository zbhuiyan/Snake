import java.util.*; 

public class SnakeBody {
  
  // Instance variables:  
  private final int GAME_SIZE = 50;
  private LinkedList<Location> body; //this is one part of the body
  
  // Constructor: given a length of a snake, make a linked list of that size.
  public SnakeBody() {
    body = new LinkedList<Location>();
    body.add(new Location(0, (GAME_SIZE/2)));
  }
    
  public LinkedList<Location> getBody () {
    return body;
  }
  
  public void move(String direction) {
    for (int i = body.size()-1; i >= 1; i--) { // Update all segments except head
      body.set(i, body.get(i-1));
    }
    
    Location head = body.get(0);
    Location new_head = head;
    
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
   * Helper method seeing if the snake goes into itself
   */
  private boolean hasDuplicates() {
    return body.lastIndexOf(body.getFirst()) != 0;
  }
  
  public void addSegment (Location tail) {
    body.add(tail);
  }
  
  /**
   * Get the string representation of the Snake Body
   */
  public String toString() {
    String s = "";
    for (int i = 0; i < body.size(); i++){
      
    }
    return s;
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