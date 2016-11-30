import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakePanel extends JPanel {
  
  // Instance variables
  private JButton quit, start, pause;
  private JLabel status, foodpile;
  private boolean paused;
  
  // Constructor
  public SnakePanel() {
    paused = false;
    
    quit = new JButton("Quit");
    start = new JButton("Restart");
    pause = new JButton("Pause");
    decorateButton(quit, new Color(255, 51, 0));
    decorateButton(start, new Color(51, 204, 51));
    decorateButton(pause, new Color(51, 102, 255));
    
    status = new JLabel("Welcome to the Snake Game!", JLabel.CENTER);
    foodpile = new JLabel("Food Consumed", JLabel.CENTER);
    
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
  }
  
  private void decorateButton(JButton button, Color color) {
    button.setBackground(color);
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setFont(new Font("Tahoma", Font.BOLD, 15));
  }
  
  private JPanel makeNorthPanel() {
    JPanel northPanel = new JPanel();
    northPanel.setBackground(Color.white);
    status.setFont(new Font("Impact", Font.PLAIN, 30));
    northPanel.add(status);
    return northPanel;
  }
  
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
  
  private JPanel makeCenterPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setBackground(Color.white);
    centerPanel.setPreferredSize(new Dimension(500, 500));
    return centerPanel;
  }
  
  private JPanel makeEastPanel() {
    JPanel eastPanel = new JPanel();
    eastPanel.setBackground(Color.white);
    eastPanel.setPreferredSize(new Dimension(200, 1000));
    foodpile.setFont(new Font("Impact", Font.PLAIN, 25));
    eastPanel.add(foodpile);
    return eastPanel;
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == quit) {
        try {
          Thread.sleep(500);
          System.exit(0);
        } catch (InterruptedException e) {
          System.exit(0);
        }
      }
      if (event.getSource() == start) {
        status.setText("Restarting the game...");
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