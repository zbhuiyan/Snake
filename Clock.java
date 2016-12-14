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
	* This method checks to see whether the clock is paused and then
	* sets the static variable as so.
	*
	* @param boolean isPaused, if the clock is paused.
	*/
	public void pause(boolean isPaused){
		this.isPaused = isPaused;
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
		this.msPerCycle = (1f/cyclesPerSec)*1000; //Number of cycles per sec. 1f specifies 1 is a float
	}


	/**
	* This method updates the clock, if the game is not paused. 
	*
	*/
	public void updateClock(){
		// The delta time calculated from subtracting last clock update from
		// the current time.
		float delta = (float)(this.getCurrentTime() - lastClockUpdate); 
		while(!isPaused){
			// Number of passed cycles is calculated by flooring the value
			// of delta divided by the number of milliseconds per cycle.
			this.numPassedCycles += (int)Math.floor(delta / msPerCycle);
		}
		this.lastClockUpdate = getCurrentTime();
	}

	/**
	* This method resets the clock.
	*
	*/
	public void reset(){
		this.isPaused = false;
		this.numPassedCycles = 0;
		this.lastClockUpdate = this.getCurrentTime();
	}

	/**
	* This method returns whether the clock is paused.
	*
	* @return boolean if the clock is paused.
	*/
	public boolean isPaused(){
		return this.isPaused;
	}

	/**
	* This method checks to see if the number of passed cycles is
	* greater than 0. If so, it decrements each time and returns true.
	* If not, it returns false.
	*
	* @return boolean if the number of passed cycles is greater or less than 0.
	*/
	public boolean hasPassedCycle(){
		if(numPassedCycles > 0){
			this.numPassedCycles--; //If the number of passed cycles is greater than 0, it will decrement		
			return true;
		}
		else {
			return false;
		}
	}




}