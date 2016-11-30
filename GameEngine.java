/**
* GameEngine.java
* The game engine is responsible for updating the game at specific
* intervals, and in response to any event. It accepts user input
* from the keyboard and handles the game's logic, game loop, and 
* window creation.
*/

import java.awt.*; //For Java Canvas class
import javax.swing.*; //For Java JFrame class

public class GameEngine{

	private Canvas canvas; //The canvas that we will interact with.
	private static final int UPDATES_PER_SEC = 5;
	private long startTime = 0L; //Must use type long to be precise with milliseconds
	private long sleepTime = 0L;

	/**
	* Here we create a constructor to make a new GameEngine.
	*
	* @param c The canvas instance that we will be interacting with. 
	*/
	public GameEngine(Canvas c){
		this.canvas = c;
	}


	/**
	* This method sets up the game.
	*
	* @param c The canvas instance that we will be interacting with. 
	*/
	public void beginGame(){
		this.canvas.createBufferStrategy(2); //organizing complex memory on the Canvas
		
		Graphics2D graphics = (Graphics2D)canvas.getBufferStrategy().getDrawGraphics(); //Must cast Graphics2D type onto the Graphics type

		while(true){ //The game loop will keep looping until the program is quit
			this.startTime = System.nanoTime(); //The exact time when the loop starts
			canvas.update(graphics); //update the canvas
			// update(); //update the game
			render(graphics);

		}
	}



	public static void main(String[] args){
		
		/**
		* Here we create a new canvas and we set its background color
		* to be white and give it a dimension of 500 by 500 pixels.
		*/
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);

		Dimension d = new Dimension(500, 500);
		canvas.setSize(d); //d.width = 500 and d.height = 500


		/**
		* Here we create a new JFrame and set it so the user cannot 
		* resize the frame and the frame closes on exit.
		*/
		JFrame jframe = new JFrame("Snake!");

		jframe.setResizable(false); //So the user cannot resize the frame
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //The frame closes but the app keeps running
		

		/**
		* Here we have to add the canvas to the frame. We then make the window
		* be sized to fit the 500 by 500 Dimension, and tell it to be visible.
		*/

		jframe.add(canvas); 
		jframe.pack(); // Causes this Window to be sized to fit the Dimension
		jframe.setVisible(true);
		jframe.toFront(); // This brings the Window to the front
	}

}