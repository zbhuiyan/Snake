import java.util.*; 

public class SnakeBody {
  
  // Instance variables:  
  private final int GAME_SIZE = 50;
  private int snakeLength;
  private int[][] game;
  private String direction;
  private LinkedList<String> body; //this is one part of the body
  
  // Constructor: given a length of a snake, make a linked list of that size.
  public SnakeBody() {
    game = new int[GAME_SIZE][GAME_SIZE];
    body = new LinkedList<String>();
    body.add("0, " + Integer.toString(GAME_SIZE/2));
    snakeLength = body.size();
  }
    
  public LinkedList<String> getBody () {
    return body;
  }
  
  public void move(String direction) {
    for (int i = body.size()-1; i >= 1; i--) {
      String new_coord = body.get(i-1);
      body.set(i, new_coord);
    }
    
    String s = body.get(0);
    int[] coords = convertCoordstoInt(s);
    if (direction.equals("R")) {
      coords[0]++;
    } else if (direction.equals("L")) {
      coords[0]--;
    } else if (direction.equals("U")) { //remember that up *subtracts* from the y-coordinate!
      coords[1]--;
    } else if (direction.equals("D")) {
      coords[1]++;
    }
    s = convertCoordstoString(coords);
    body.set(0, s);
  }
  
  private int[] convertCoordstoInt (String input) {
    String[] coords = input.split(", ");
    int[] finalcoords = new int[2];
    
    finalcoords[0] = Integer.parseInt(coords[0]);
    finalcoords[1] = Integer.parseInt(coords[1]);
    
    return finalcoords;
  }
  
    private String convertCoordstoString (int[] input) {
    String finalcoords = input[0] + ", " + input[1];
    return finalcoords;
  }
  
  public boolean isDead() {
    String head = body.get(0); // retrieve the head of the snake
    int[] coords = convertCoordstoInt(head);
    if (coords[0]<0 || coords[0]>50 || coords[1]<0 || coords[1]>50) {
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
  
  public void addSegment (String tail) {
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
    String tail = snake.getBody().getLast();
    snake.move("L");
    snake.addSegment(tail);
    System.out.println(snake.getBody());
  }
}