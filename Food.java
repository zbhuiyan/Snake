/** Returns the original Food hastable. */

import java.util.*;

public class Food {
  
  private Hashtable<Integer, String> food;
  
  /**
   * This constructor method creates a hashtable and puts 6 different
   * types of food into it.
   *  
   */ 
  public Food () {
    food = new Hashtable<Integer, String>();
    food.put(1, "Red");
    food.put(2, "Orange");
    food.put(3, "Yellow");
    food.put(4, "Green");
    food.put(5, "Blue");
    food.put(6, "Purple");
  }
  
  /**
   * This method tries to pause the game.
   *  
   */ 
  public Hashtable<Integer, String> getFood() {
    return food;
  }
  
  /**
   * This method gets a random food item.
   *  
   * @return String
   */ 
  public String randomFood() {
    Random r = new Random();
    int n = r.nextInt(6) + 1;
    return food.get(n);
  }
  
  public static void main (String[] args) {
    Food test = new Food();
    System.out.println(test.getFood());
  }
}