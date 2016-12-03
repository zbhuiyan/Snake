/**
* Clock.java
* This class keeps track of how many cycles have passed throughout the game.
*/

public class Clock {

	private boolean isPaused; // If the game is paused
	private float msPerCycle; //How many milliseconds make up a single cycle in 32 bit precision
	private long lastClockUpdate; //64 bit precision
	private int numPassedCycles;


	/**
	* This constructs a new Clock object.
	*
	* @param float cyclesPerSec This is the number of cycles that pass each second.
	*/
	public Clock(float cyclesPerSec){
		setCyclesPerSec(cyclesPerSec);
		this.lastClockUpdate = getCurrentTime();
	}


	/**
	* This method gets the current time of the game. It calculates this
	* in milliseconds and is very precise and quick.
	*
	* @return final long The current time of the game in milliseconds.
	*/
	public static final long getCurrentTime(){
		return (System.nanoTime()/1000000L); 
	}


	/**
	* This method sets the number of cycles per second.
	*
	* @param float cyclesPerSec This is the number of cycles that pass each second.
	*/
	public void setCyclesPerSec(float cyclesPerSec){
		this.msPerCycle = (1f/cyclesPerSec)*1000; //Number of cycles per cycle. 1f specifies 1 is a float
	}


	/**
	* This method updates the clock, if the game is not paused. 
	*
	*/
	public void updateClock(){
		// float delta = (float)(getCurrentTime() - 
	}


}