import java.util.*;

public class Food {
  
  private Hashtable<String,Integer> food;
  
  public Food () {
    food = new Hashtable<String,Integer>();
    food.put("Red", 1);
    food.put("Orange", 2);
    food.put("Yellow", 3);
    food.put("Green", 4);
    food.put("Blue", 5);
    food.put("Purple", 6);
  }
  
  public Hashtable<String,Integer> getFood() {
    return food;
  }
  
  public static void main (String[] args) {
    Food test = new Food();
    System.out.println(test.getFood());
  }
}