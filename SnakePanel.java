import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakePanel extends JPanel {
  
  // Instance variables
  private JButton quit, start, pause;
  private JLabel status, foodpile;
  private boolean paused;
  private JButton[][] pixels;
  private JButton[][] foodQueue;
  
  /**
   * Constructor: Instantiates all instance variables.
   */
  public SnakePanel() {
    paused = false;
    
    // Game state buttons
    quit = new JButton("Quit");
    start = new JButton("Restart");
    pause = new JButton("Pause");
    
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
    JFrame frame = new JFrame("Snake!");
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
   * Helper method: Creates west panel.
   * @return  West JPanel; doesn't contain anything
   */
  private JPanel makeWestPanel() {
    JPanel westPanel = new JPanel();
    westPanel.setLayout(new BorderLayout(20, 20));
    return westPanel;
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
          status.setText("Welcome to the Snake Game!"); // Continue the game, nothing here right now
        } else {
          status.setText("Game paused.");
          paused = true;
        }
      }
    }
  }
  
  public static void main (String [] args) {
    new SnakePanel();
  }
  
}