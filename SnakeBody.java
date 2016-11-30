//import javafoundations.*;
import javafoundations.exceptions.*;
import java.util.*; 

public class SnakeBody {
  // Instance variables:
  private LinkedList<String> body;
  private int snakeLength;
  private int[][] game = new int[50][50];
  
  // Constructor: given a length of a snake, make a linked list of that size.
  public SnakeBody() {
    this.length = length;
    //Makes a new empty linked list
    LinkedList<String> body = new LinkedList<String>;
  }
    
  /**
   * Method for getting the updated coordinates, or the snake's next motion, given an array
   */
  public void getNextState() {
    //Assuming we have a complete snake with everything we need:
    //get the second element's coordinates by removing the head and accessing the new head's string
    
    
    //then add the original head back to the front using the addFirst method
    
    //Get delta x-coords and delta y-coords, delete the last element and add a new element to the front
    //the new element should represent the (x_head + delta_x, y_head + delta_y)
    
    
    //Given the new linkedlist, plot the results on a new array and make that the new game array
  }
  
  
  /** 
   * Helper method for getting the length of the snake
   */
  private int getLength() {
    
  }
  
  public String toString() {
    s = ""
    return s;
  }
  
  public static void main(String[] args) {
    SnakeBody snake = new Snakebody();
    System.out.println(snake);
    //Using LinearNodes in a list:
    String head1 = "1 1"; // first head
    String head2 = "1 2"; // add part two
    String head3 = "1 3"; // add part three
    
    LinearNode<String> front; //refers to the first node in list
    LinearNode<String> temp; //refers to new node as it is being added to list
    front = new LinearNode<String>(head2); //List should contain two nodes, I hope.
    
    
    
    
  }
}