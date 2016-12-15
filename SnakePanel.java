/**
 * SnakePanel.java
 * This class contains the GUI for the Snake game.
 * Primarily worked on by Emily Yeh, Zarin Bhuiyan, and Anne Ku.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SnakePanel extends JPanel {
  
  // Instance variables
  private JFrame frame; // GUI frame
  private JButton quit, start, pause; // Buttons
  private JButton[][] pixels, foodQueue; // "Grids" of buttons
  private JLabel status, foodpile; // Labels
  
  private boolean paused; // Flag (indicates whether game is paused)
  private String pointCounter; // Supplement to status JLabel
  private Queue<String> directions; // Queue of directions
  private LinkedList<String> colorQueue; // Queue of foods eaten
  
  private Food food; // Food hashtable
  
  /**
   * Constructor: Instantiates all instance variables.
   */
  public SnakePanel() {
    directions = new LinkedList<String>();
    colorQueue = new LinkedList<String>();
    food = new Food();
    paused = false;
    
    // Game state buttons
    quit = new JButton("Quit");
    start = new JButton("Restart");
    pause = new JButton("Pause");
    
    // Decorate game state buttons
    decorateButton(quit, new Color(255, 51, 0));
    decorateButton(start, new Color(51, 204, 51));
    decorateButton(pause, new Color(51, 102, 255));
    
    // Center panel (main gameplay) buttons
    pixels = new JButton[50][50];
    for (int i = 0; i < 50; i++) {
      for (int j = 0; j < 50; j++) {
        pixels[i][j] = new JButton("");
        pixels[i][j].setPreferredSize(new Dimension(10, 10));
        decorateButton(pixels[i][j], Color.white);
      }
    }
    
    // East panel (food queue) buttons
    foodQueue = new JButton[10][1];
    for (int i = 0; i < 10; i++) {
      foodQueue[i][0] = new JButton("");
      foodQueue[i][0].setAlignmentX(Component.CENTER_ALIGNMENT);
      decorateButton(foodQueue[i][0], Color.WHITE);
      
    }
    
    // Labels
    status = new JLabel("Welcome to the Snake Game!", JLabel.CENTER);
    foodpile = new JLabel("Food", JLabel.CENTER);
    
    // Create and customize frame
    frame = new JFrame("Snake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
    
    frame.setLayout(new BorderLayout(10, 10));
    frame.setSize(1200, 1000);
    frame.add(makeNorthPanel(), BorderLayout.NORTH);
    frame.add(makeSouthPanel(), BorderLayout.SOUTH);
    frame.add(makeCenterPanel(), BorderLayout.CENTER);
    frame.add(makeEastPanel(), BorderLayout.EAST);
    frame.add(makeWestPanel(), BorderLayout.WEST);
    
    // Keyboard listening   
    SnakeKeyListener keyListener = new SnakeKeyListener();
    frame.addKeyListener(keyListener);
    frame.requestFocus(); // Ensure keyboard presses are detected
  }
  
  /**
   * Updates game score in top of GUI.
   * 
   * @param   new score
   */
  public void updateScore (int score) {
    pointCounter = Integer.toString(score); // Update point counter
    status.setText("Welcome to the Snake Game! Current score: " + pointCounter);
  }
  
  /**
   * Updates food queue on side of GUI.
   * 
   * @param  point value of food (to look up in hash table)
   */
  public void updateFood (int foodValue) {
    String color = food.getFood().get(foodValue);
    colorQueue.add(color);
    if (colorQueue.size() < 11) { // Food queue can't be larger than 10 boxes
      decorateButton(foodQueue[colorQueue.size()-1][0], getColor(color));
    } else { // Once food queue is larger than 10 boxes, dequeue and shift colors up
      colorQueue.remove();
      for (int i = 0; i < colorQueue.size(); i++) {
        decorateButton(foodQueue[i][0], getColor(colorQueue.get(i)));
      }
    }
  }
  
  /**
   * Places food in central panel.
   * 
   * @param   location of food
   * @return  point value of food placed
   */
  public int placeFood (Location location) {
    int x = location.getX();
    int y = location.getY();
    Random r = new Random();
    int pointValue = r.nextInt(5) + 1;
    String color = food.getFood().get(pointValue);
    decorateButton(pixels[y][x], getColor(color));
    return pointValue;
  }
  
  /**
   * Moves and places segments of snake body in central panel.
   * 
   * @param   LinkedList body of snake
   */ 
  public void moveSnake (LinkedList<Location> body, Location tail) {
    try {
      for (int i = 0; i < body.size(); i++) {
        Location current = body.get(i);
        int x = current.getX();
        int y = current.getY();
        decorateButton(pixels[y][x], Color.BLACK);
      }
      
      int tail_x = tail.getX();
      int tail_y = tail.getY();
      decorateButton(pixels[tail_y][tail_x], Color.WHITE);
    } catch (ArrayIndexOutOfBoundsException ex) {
      status.setText("Your snake died! Final score: " + pointCounter);
    }
    frame.requestFocus();
  }
  
  /**
   * Retrieves the current direction.
   *
   * @return direction  
   */
  public String getCurrentDirection(){
    return this.directions.remove(); 
  }
  
  /**
   * Indicator of the state of the game (running vs. paused).
   * 
   * @return whether game is paused
   */
  public boolean getPaused () {
    return paused;
  }
  
  /**
   * Helper method: Decorates button with given background color.
   * @param   button   JButton to be decorated
   * @param   color    background color for button
   */
  private void decorateButton(JButton button, Color color) {
    button.setBackground(color);
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setFont(new Font("Tahoma", Font.PLAIN, 15));
  }
  
  /**
   * Helper method: Creates north panel.
   * @return  North JPanel; contains status label
   */
  private JPanel makeNorthPanel() {
    JPanel northPanel = new JPanel();
    northPanel.setBackground(Color.white);
    status.setFont(new Font("Tahoma", Font.PLAIN, 30));
    northPanel.add(status);
    return northPanel;
  }
  
  /**
   * Helper method: Creates south panel.
   * @return  South JPanel; contains game state buttons
   */
  private JPanel makeSouthPanel() {
    JPanel southPanel = new JPanel();
    southPanel.setBackground(Color.white);
    
    quit.addActionListener(new ButtonListener());
    southPanel.add(quit);
    start.addActionListener(new ButtonListener());
    southPanel.add(start);
    pause.addActionListener(new ButtonListener());
    southPanel.add(pause);
    return southPanel;
  }
  
  /**
   * Helper method: Creates center panel.
   * @return  Center JPanel; contains main gameplay screen
   */
  private JPanel makeCenterPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setBackground(Color.white);
    centerPanel.setPreferredSize(new Dimension(500, 500));
    centerPanel.setLayout(new GridLayout(50, 50)); // rows, columns
    
    for (int i = 0; i < 50; i++) {
      for (int j = 0; j < 50; j++) {
        centerPanel.add(pixels[i][j]);
      }
    }
    
    centerPanel.setVisible(true);
    return centerPanel;
  }
  
  /**
   * Helper method: Creates east panel.
   * @return  East JPanel; contains food queue
   */
  private JPanel makeEastPanel() {
    JPanel eastPanel = new JPanel(new GridLayout(0, 1, 0, 3));
    eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
    eastPanel.setOpaque(false);
    foodpile.setFont(new Font("Tahoma", Font.PLAIN, 20));
    eastPanel.add(foodpile);
    
    for (int i = 0; i < 10; i++) {
      foodQueue[i][0].setPreferredSize(new Dimension(75, 75));
      eastPanel.add(foodQueue[i][0]);
    }
    
    // Need to put eastPanel into another panel to ensure all the components are centered
    JPanel rightPanel = new JPanel();
    rightPanel.setBackground(Color.WHITE);
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
    rightPanel.add(Box.createVerticalGlue());
    rightPanel.add(eastPanel);
    rightPanel.add(Box.createVerticalGlue());
    
    return rightPanel;
  }
  
  /**
   * Helper method: Retrieves color to color a button with.
   * 
   * @param   intended color of button
   * @return  java color value
   */
  private Color getColor (String color) {
    if (color.equals("Red")) return Color.RED;
    else if (color.equals("Orange")) return Color.ORANGE;
    else if (color.equals("Yellow")) return Color.YELLOW;
    else if (color.equals("Green")) return Color.GREEN;
    else if (color.equals("Blue")) return Color.BLUE;
    else return Color.MAGENTA;
  }
  
  /**
   * Helper method: Creates west panel.
   * @return  West JPanel; doesn't contain anything
   */
  private JPanel makeWestPanel() {
    JPanel westPanel = new JPanel();
    westPanel.setLayout(new BorderLayout(20, 20));
    return westPanel;
  }
  
  /**
   * SnakeKeyListener is a private class that checks for new directions from user input.
   */
  private class SnakeKeyListener implements KeyListener {
    public void keyReleased(KeyEvent e) {
      keyPressed(e); // Do the same thing keyPressed does
    }
    
    public void keyTyped(KeyEvent e) {
      keyPressed(e); // Do the same thing keyPressed does
    }
    
    public void keyPressed(KeyEvent e) {
      int keyCode = e.getKeyCode(); //getKeyCode returns the integer KeyCode associated with the key
      
      switch(keyCode){ 
        case KeyEvent.VK_UP:
          directions.add("U");
          break;
        case KeyEvent.VK_DOWN:
          directions.add("D");
          break;
        case KeyEvent.VK_LEFT:
          directions.add("L");
          break;
        case KeyEvent.VK_RIGHT:
          directions.add("R");
          break;
      }
    }
  }
  
  /**
   * ButtonListener is a private class for responding to button push events.
   */
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == quit) {
        System.exit(0);
      }
      
      if (event.getSource() == start) {
        status.setText("Starting a new game...");
        new SnakePanel(); 
      }
      
      if (event.getSource() == pause) {
        if (paused) {
          paused = false;
          status.setText("Welcome to the Snake Game! Current score: " + pointCounter); // Continue the game
          frame.requestFocus();
        } else {
          status.setText("Game paused. Current score: " + pointCounter);
          paused = true;
        }
      }
    }
  }
  
  /**
   * Main method (for debugging and testing).
   */
  public static void main (String [] args) {
    new SnakePanel();
  }
}