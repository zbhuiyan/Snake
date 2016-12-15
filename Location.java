/**
 * Location takes takes in x & y coordinates to 
 * define a specific coordinate in the Snake game grid.
 */

public class Location {
  
  private int x;
  private int y;
  
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Getter and setter methods for each coordinate component:
   */
  public int getX () {
    return x;
  }
  
  public int getY () {
    return y;
  }
  
  public void setX (int x) {
    this.x = x;
  }
  
  public void setY (int y) {
    this.y = y;
  }
  
  public boolean equals (Location other) {
    int otherX = other.getX();
    int otherY = other.getY();
    if ((x == otherX) && (y == otherY)) return true;
    else return false;
  }
    
  /**
   * Returns a String representation of the location class
   */
  public String toString () {
    return "(" + x + ", " + y + ")";
  }
  
  public static void main (String [] args) {
    Location test = new Location(5, 2);
    System.out.println(test);
  }

}